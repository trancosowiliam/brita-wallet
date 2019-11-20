package br.com.britawallet.feature.exchange

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter

class ExchangeActivity : AppCompatActivity(), ExchangeContract.View {
    override val presenter by injectPresenter(this)

    companion object {
        operator fun invoke(context: Context): Intent {
            return Intent(context, ExchangeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)

        setupViews()
    }

    private fun setupViews() {
    }
}