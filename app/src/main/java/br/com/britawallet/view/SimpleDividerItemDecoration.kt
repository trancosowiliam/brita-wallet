package br.com.britawallet.view

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.britawallet.R

class SimpleDividerItemDecoration(
    context: Context,
    private val margin: Int = context.resources.getDimensionPixelSize(R.dimen.margin_normal)
) :
    RecyclerView.ItemDecoration() {
    private val divider =
        ContextCompat.getDrawable(context, R.drawable.simple_separator)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        divider?.let {
            val left = margin
            val right = parent.width - margin

            val childCount = parent.childCount

            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + it.intrinsicHeight

                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }
        }
    }
}