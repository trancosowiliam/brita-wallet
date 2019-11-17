package br.com.britawallet.feature.reward

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter
import br.com.britawallet.base.extensions.setSafeOnClickListener
import br.com.britawallet.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_reward.*

class RewardActivity : AppCompatActivity(), RewardContract.View {
    override val presenter by injectPresenter(this)

    companion object {
        operator fun invoke(context: Context): Intent {
            return Intent(context, RewardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)

        setupViews()
    }

    private fun setupViews() {
        todoGotoMain.setSafeOnClickListener {
            startActivity(MainActivity(this))
            finish()
        }
    }
}