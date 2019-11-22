package br.com.britawallet.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import br.com.britawallet.R
import kotlinx.android.synthetic.main.wallet_toolbar.view.*

class WalletToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        View.inflate(context, R.layout.wallet_toolbar, this)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.WalletToolbar, defStyleAttr, 0)

        val textLabelColor = ta.getColor(R.styleable.WalletToolbar_wt_textLabelColor, 0x00ffffff)
        val textValueColor = ta.getColor(R.styleable.WalletToolbar_wt_textValueColor, 0x00ffffff)
        val separatorColor = ta.getColor(R.styleable.WalletToolbar_wt_separatorColor, 0x00ffffff)
        val background = ta.getColor(R.styleable.WalletToolbar_wt_background, 0x00ffffff)

        wtLblBalance.setTextColor(textLabelColor)
        wtTxtBalance.setTextColor(textValueColor)
        this.setBackgroundColor(background)
        wtViewBottomActionBar.setBackgroundColor(separatorColor)

        ta.recycle()
    }
}