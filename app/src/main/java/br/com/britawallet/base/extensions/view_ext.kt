package br.com.britawallet.base.extensions

import android.os.SystemClock
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText

private class SafeClickListener(
    private var defaultInterval: Int = 2300,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval)
            return

        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }

    setOnClickListener(safeClickListener)
}

fun AppCompatEditText.onActionListener(function: () -> Unit) {
    this.setOnEditorActionListener { _, id, _ ->
        when (id) {
            EditorInfo.IME_ACTION_DONE,
            EditorInfo.IME_ACTION_SEND,
            EditorInfo.IME_ACTION_SEARCH,
            EditorInfo.IME_ACTION_GO -> {
                function()
                return@setOnEditorActionListener true
            }
            else -> return@setOnEditorActionListener false
        }
    }
}