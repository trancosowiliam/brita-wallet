package br.com.britawallet.feature.main

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView

interface MainContract {
   interface View : BaseView<Presenter> {
   }

   interface Presenter : BasePresenter<View> {
   }
}