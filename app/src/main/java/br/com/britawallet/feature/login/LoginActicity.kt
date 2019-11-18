package br.com.britawallet.feature.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter
import br.com.britawallet.base.extensions.onActionListener
import br.com.britawallet.base.extensions.setSafeOnClickListener
import br.com.britawallet.base.extensions.showMessageDialog
import br.com.britawallet.base.extensions.showTodoDialog
import br.com.britawallet.base.extensions.toast
import br.com.britawallet.feature.home.HomeActivity
import br.com.britawallet.feature.reward.RewardActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    override val presenter by injectPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.checkAppUser()
    }

    override fun setupViews() {
        logGroupViews.isVisible = true

        logEdtPassword.onActionListener {
            login()
        }

        logBtnLogin.setSafeOnClickListener {
            login()
        }

        logBtnRegister.setSafeOnClickListener {
            presenter.register()
        }

        logBtnForgotPassword.setSafeOnClickListener {
            presenter.forgotPassword()
        }
    }

    override fun showLoginLoading() {
        logFrLoading.isVisible = true
    }

    override fun hideLoginLoading() {
        logFrLoading.isVisible = false
    }

    override fun showLoginFailureMessage(message: String) {
        showMessageDialog(
            message,
            getString(R.string.dialog_error_title)
        )
    }

    override fun showLoginFieldError(message: String) {
        toast(message)
    }

    override fun showPasswordFieldError(message: String) {
        toast(message)
    }

    override fun goToReward() {
        startActivity(RewardActivity(this))
    }

    override fun goToHome() {
        startActivity(HomeActivity(this))
    }

    override fun closeWindow() {
        finish()
    }

    override fun goToRegister() {
        showTodoDialog()
    }

    override fun showForgotPasswordDialog() {
        showTodoDialog()
    }

    fun login() {
        presenter.login(
            logEdtLogin.text.toString(),
            logEdtPassword.text.toString()
        )
    }
}