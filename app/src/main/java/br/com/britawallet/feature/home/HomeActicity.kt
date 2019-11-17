package br.com.britawallet.feature.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter
import br.com.britawallet.base.extensions.setSafeOnClickListener
import br.com.britawallet.data.local.UserLocalRepository
import br.com.britawallet.feature.login.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity(), HomeContract.View {
    override val presenter by injectPresenter(this)
    private val userLocalRepository by inject<UserLocalRepository>()

    companion object {
        operator fun invoke(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViews()
    }

    private fun setupViews() {
        todoLogout.setSafeOnClickListener {
            userLocalRepository.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}