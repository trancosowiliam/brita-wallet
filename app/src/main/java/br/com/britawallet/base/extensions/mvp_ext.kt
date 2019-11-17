package br.com.britawallet.base.extensions

import android.content.ComponentCallbacks
import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier

inline fun <reified T : BasePresenter<*>> ComponentCallbacks.injectPresenter(
    view: BaseView<T>,
    qualifier: Qualifier? = null
) = lazy { get<T>(qualifier) { parametersOf(view) } }