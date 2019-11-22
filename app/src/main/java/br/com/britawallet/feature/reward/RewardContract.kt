package br.com.britawallet.feature.reward

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView

interface RewardContract {
   interface View : BaseView<Presenter> {
      fun goToHome()
      fun closeWindow()
   }

   interface Presenter : BasePresenter<View> {
      fun begin()
   }
}