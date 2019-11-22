package br.com.britawallet.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.britawallet.R
import br.com.britawallet.base.extensions.setColor
import br.com.britawallet.base.extensions.setResTextColor
import br.com.britawallet.base.extensions.setSafeOnClickListener
import br.com.britawallet.base.extensions.toCurrency
import kotlinx.android.synthetic.main.item_balance.view.*

class WalletAdapter : RecyclerView.Adapter<WalletAdapter.WalletHolder>() {

    var data = emptyList<BalanceHomeData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemAddClick: ((BalanceHomeData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WalletHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_balance,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WalletHolder, position: Int) =
        holder.render(data[position])

    inner class WalletHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.ibaBtnAdd.setSafeOnClickListener {
                data[adapterPosition].let { item ->
                    onItemAddClick?.invoke(item)
                }
            }
        }

        fun render(item: BalanceHomeData) {
            itemView.ibaTxtCurrency.text = item.name
            itemView.ibaLblCurrency.text = item.description
            itemView.ibaTxtValue.text = item.quantity.toCurrency(item.symbol)
            itemView.ibaCardContainer.background.setColor(itemView.context, item.color)
            itemView.ibaBtnAdd.setResTextColor(item.color)

            itemView.ibaImgCurrencySymbol.setImageResource(item.icon)
        }
    }
}