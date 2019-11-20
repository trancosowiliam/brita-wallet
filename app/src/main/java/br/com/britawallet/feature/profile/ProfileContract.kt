package br.com.britawallet.feature.profile

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView

interface ProfileContract {
   interface View : BaseView<Presenter> {
   }

   interface Presenter : BasePresenter<View> {
   }
}