package br.com.britawallet.feature.exchange

import br.com.britawallet.base.di.testModules
import br.com.britawallet.data.local.mockResources
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.Currency.Companion.BRL
import br.com.britawallet.data.model.Currency.Companion.BRT
import br.com.britawallet.data.model.Currency.Companion.BTC
import br.com.britawallet.data.model.User
import br.com.britawallet.data.remote.initMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.ArgumentMatchers.anyDouble

class ExchangePresenterTest : AutoCloseKoinTest() {
    private val view: ExchangeContract.View by inject()

    private val presenter by inject<ExchangePresenter> {
        parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

    @Test
    fun `test when setup window then show balance`() = runBlocking {
        presenter.staticResources.mockResources()
        presenter.quotationRepository.initMock()

        presenter.setupTransaction()

        verify(view).showBalance(argThat {
            Currency[this.currencyType].isDefault
        })
    }

    @Test
    fun `test when setup window with unloaded currency then load currency`() = runBlocking {
        presenter.staticResources.mockResources()
        presenter.quotationRepository.initMock()

        Currency.isLoaded = false

        presenter.setupTransaction()

        verify(view).showLoading()
        verify(presenter.quotationRepository).getQuotation()
        verify(view).changeCurrencyFrom(any())
        verify(view).changeCurrencyTo(any())
        verify(view).changeValueFrom(anyDouble())
        verify(view).changeValueTo(1.0)
        verify(view).hideLoading()
    }

    @Test
    fun `test when setup window with loaded currency then show currency`() = runBlocking {
        presenter.staticResources.mockResources()
        presenter.quotationRepository.initMock()

        Currency.isLoaded = true

        presenter.setupTransaction()

        verify(view).changeCurrencyFrom(any())
        verify(view).changeCurrencyTo(any())
        verify(view).changeValueFrom(anyDouble())
        verify(view).changeValueTo(1.0)

        verify(view, never()).showLoading()
        verify(presenter.quotationRepository, never()).getQuotation()
        verify(view, never()).hideLoading()
    }

    @Test
    fun `test when change from currency then recalculate`() {
        val from = BRL
        val to = BRT
        val fromValue = 40.0
        val toValue = from.convert(fromValue, to)
        val new = BTC

        presenter.changeFromCurrency(from, to, fromValue, toValue, new)

        verify(view).changeCurrencyFrom(new)
        verify(view).changeValueFrom(from.convert(toValue, new))
    }

    @Test
    fun `test when change to currency then recalculate`() {
        val from = BRL
        val to = BRT
        val toValue = 40.0
        val fromValue = to.convert(toValue, from)
        val new = BTC

        presenter.changeToCurrency(from, to, fromValue, toValue, new)

        verify(view).changeCurrencyTo(new)
        verify(view).changeValueTo(to.convert(fromValue, new))
    }

    @Test
    fun `test when change from currency to equal then switch`() {
        presenter.changeFromCurrency(
            from = BRL,
            to = BRT,
            fromValue = 1.0,
            toValue = 2.0,
            new = BRT
        )

        verify(view).changeCurrencyFrom(BRT)
        verify(view).changeCurrencyTo(BRL)
        verify(view).changeValueFrom(2.0)
        verify(view).changeValueTo(1.0)
    }

    @Test
    fun `test when change to currency to equal then switch`() {
        presenter.changeToCurrency(
            from = BRL,
            to = BRT,
            fromValue = 1.0,
            toValue = 2.0,
            new = BRL
        )

        verify(view).changeCurrencyFrom(BRT)
        verify(view).changeCurrencyTo(BRL)
        verify(view).changeValueFrom(2.0)
        verify(view).changeValueTo(1.0)
    }

    @Test
    fun `test when switch currency then show reverse`() {
        presenter.switchCurrency(
            from = BRL,
            to = BRT,
            fromValue = 1.0,
            toValue = 2.0
        )

        verify(view).changeCurrencyFrom(BRT)
        verify(view).changeCurrencyTo(BRL)
        verify(view).changeValueFrom(2.0)
        verify(view).changeValueTo(1.0)
    }

    @Test
    fun `test when exchange with insufficient funds then show message`() {
        presenter.staticResources.mockResources(
            wallet = listOf(
                User.Balance(BRL.type, 300.0),
                User.Balance(BRT.type, 600.0)
            )
        )

        presenter.onExchange(
            from = BRL,
            to = BRT,
            fromValue = 301.0,
            toValue = 2.0
        )

        verify(view).showInsufficientFundsMessage()

        verifyNoMoreInteractions(
            view,
            presenter.userRemoteRepository,
            presenter.transactionRemoteRepository
        )
    }

    @Test
    fun `test when successful exchange then att balance and persist`() = runBlocking {
        presenter.staticResources.mockResources(
            wallet = listOf(
                User.Balance(BRL.type, 300.0),
                User.Balance(BRT.type, 600.0)
            )
        )

        presenter.onExchange(
            from = BRL,
            to = BRT,
            fromValue = 8.0,
            toValue = 2.0
        )

        verify(presenter.userRemoteRepository).attUserWallet(any())
        verify(presenter.transactionRemoteRepository).save(any())
        verify(view).showSuccessfulTransaction()

        verify(view, never()).showInsufficientFundsMessage()
    }
}