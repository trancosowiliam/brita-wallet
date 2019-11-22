package br.com.britawallet.base.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat

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

fun TextView.setResTextColor(resColor: Int) {
    setTextColor(ContextCompat.getColor(this.context, resColor))
}

fun Drawable?.setColor(context: Context, resColor: Int) {
    val color = ContextCompat.getColor(context, resColor)

    when (this) {
        is ShapeDrawable -> {
            paint.color = color
        }
        is GradientDrawable -> {
            setColor(color)
        }
        is ColorDrawable -> {
            setColor(color)
        }
    }
}