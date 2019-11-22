package br.com.britawallet.feature.profile

import br.com.britawallet.data.local.StaticResources
import br.com.britawallet.data.local.UserLocalRepository
import br.com.britawallet.feature.analytics.Analytics

class ProfilePresenter(
    override var view: ProfileContract.View,
    internal val staticResources: StaticResources,
    internal val userLocalRepository: UserLocalRepository,
    internal val analytics: Analytics
) : ProfileContract.Presenter {

    override fun loadUser() {
        staticResources.user?.let {
            view.showUserData(it)
        }
    }

    override fun onLogout(login: String) {
        userLocalRepository.logout()
        analytics.onLogout(login)
        staticResources.user = null
        view.close()
    }

    override fun onDeleteAccount(login: String) {
        analytics.onDeleteAccount(login)
        view.showDeleteAccountDialog()
    }

    override fun onEditUser(login: String) {
        analytics.onEditUser(login)
        view.goToEditUser(staticResources.user!!)
    }
}