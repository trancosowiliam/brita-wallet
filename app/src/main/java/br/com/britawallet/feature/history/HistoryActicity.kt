package br.com.britawallet.feature.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter

class HistoryActivity : AppCompatActivity(), HistoryContract.View {
    override val presenter by injectPresenter(this)

    companion object {
        operator fun invoke(context: Context): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setupViews()
    }

    private fun setupViews() {
    }
}