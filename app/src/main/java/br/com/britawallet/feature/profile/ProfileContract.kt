package br.com.britawallet.feature.profile

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView
import br.com.britawallet.data.model.User

interface ProfileContract {
    interface View : BaseView<Presenter> {
        fun showUserData(user: User)
        fun showDeleteAccountDialog()
        fun goToEditUser(user: User)
        fun close()
    }

    interface Presenter : BasePresenter<View> {
        fun loadUser()
        fun onLogout(login: String)
        fun onDeleteAccount(login: String)
        fun onEditUser(login: String)
    }
}