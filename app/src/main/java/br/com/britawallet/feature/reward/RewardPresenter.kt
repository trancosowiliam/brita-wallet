package br.com.britawallet.feature.reward

class RewardPresenter(
    override var view: RewardContract.View
) : RewardContract.Presenter {
    override fun begin() {
        view.goToHome()
        view.closeWindow()
    }
}