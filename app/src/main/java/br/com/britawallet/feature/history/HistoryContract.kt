package br.com.britawallet.feature.history

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView

interface HistoryContract {
   interface View : BaseView<Presenter> {
   }

   interface Presenter : BasePresenter<View> {
   }
}