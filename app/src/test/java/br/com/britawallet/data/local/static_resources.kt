package br.com.britawallet.data.local

import br.com.britawallet.data.model.User
import br.com.britawallet.data.model.currencyToBalance
import br.com.britawallet.data.model.mockUser
import com.nhaarman.mockitokotlin2.whenever

fun StaticResources.mockResources(
    login: String = "login",
    name: String = "name",
    wallet: List<User.Balance> = currencyToBalance(),
    isFirstLogin: Boolean = false
) {
    whenever(this.user).thenReturn(
        mockUser(
            login = login,
            name = name,
            wallet = wallet,
            isFirstLogin = isFirstLogin
        )
    )
}