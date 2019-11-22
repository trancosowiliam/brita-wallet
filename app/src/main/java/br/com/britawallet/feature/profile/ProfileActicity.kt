package br.com.britawallet.feature.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.britawallet.BuildConfig
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter
import br.com.britawallet.base.extensions.setSafeOnClickListener
import br.com.britawallet.base.extensions.showTodoDialog
import br.com.britawallet.data.model.User
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfileContract.View {
    override val presenter by injectPresenter(this)

    lateinit var user: User

    companion object {

        private const val PROFILE_REQUEST_CODE = 13001

        private operator fun invoke(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }

        fun startActivityForResult(activity: Activity) {
            val intent = ProfileActivity(activity)
            activity.startActivityForResult(intent, PROFILE_REQUEST_CODE)
        }

        fun isOrigin(requestCode: Int) = PROFILE_REQUEST_CODE == requestCode
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupViews()
        presenter.loadUser()
    }

    override fun showUserData(user: User) {
        this.user = user
        proTxtUser.text = user.name
    }

    override fun showDeleteAccountDialog() {
        showTodoDialog()
    }

    override fun goToEditUser(user: User) {
        showTodoDialog()
    }

    override fun close() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun setupViews() {
        proBtnProfile.setSafeOnClickListener {
            presenter.onEditUser(user.login)
        }

        proBtnLogout.setSafeOnClickListener {
            presenter.onLogout(user.login)
        }

        proBtnDeleteAcconout.setSafeOnClickListener {
            presenter.onDeleteAccount(user.login)
        }

        proLblAppDescription.text = getString(R.string.profile_app_description, BuildConfig.VERSION_NAME)
    }
}