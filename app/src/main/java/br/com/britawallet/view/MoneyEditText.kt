package br.com.britawallet.view

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import br.com.britawallet.base.extensions.toCurrency
import java.math.BigDecimal

class MoneyEditText : AppCompatEditText {

    private val regex = "[^\\d]".toRegex()
    private var dividend = BigDecimal(100)
    private var precision = 2
    private lateinit var textWatcher: TextWatcher

    var onValueChange: (Double) -> Unit = {}

    val value: Double
        get() {
            return parse(unMask(text.toString()))?.toDouble() ?: 0.0
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        inputType = InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_SIGNED
        textWatcher = object : TextWatcher {
            var isUpdating = false
            var old = ""

            override fun afterTextChanged(editable: Editable?) {
                onValueChange(value)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val unMask = unMask(s.toString())

                if (isUpdating) {
                    old = unMask
                    isUpdating = false
                    return
                }

                val parsed = parse(unMask)
                isUpdating = true

                if (parsed == null) {
                    setText("")
                } else {
                    setText(parsed.toCurrency())
                    setSelection(text?.length ?: 0)
                }
            }
        }

        addTextChangedListener(textWatcher)
    }

    fun setText(text: String, notify: Boolean) {
        if (notify) {
            setText(text)
        } else {
            val onValueChange = this.onValueChange
            this.onValueChange = {}

            setText(text)
            this.onValueChange = onValueChange
        }
    }

    private fun parse(s: String): BigDecimal? {
        return s.toSafeBigDecimal().setScale(precision, BigDecimal.ROUND_FLOOR)
            .divide(dividend, BigDecimal.ROUND_FLOOR)
    }

    private fun String.toSafeBigDecimal(): BigDecimal =
        toBigDecimalOrNull() ?: BigDecimal.ZERO

    private fun unMask(s: String) =
        regex.replace(s, "")
}
