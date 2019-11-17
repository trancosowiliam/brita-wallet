package br.com.britawallet.feature.home

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView

interface HomeContract {
   interface View : BaseView<Presenter> {
   }

   interface Presenter : BasePresenter<View> {
   }
}