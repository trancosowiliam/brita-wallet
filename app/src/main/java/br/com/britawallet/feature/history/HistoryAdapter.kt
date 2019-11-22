package br.com.britawallet.feature.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.britawallet.R
import kotlinx.android.synthetic.main.item_transaction.view.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    var data = emptyList<TransactionHistoryData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_transaction,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) =
        holder.render(data[position])

    inner class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun render(item: TransactionHistoryData) {
            itemView.itraImgBuy.setImageResource(item.buyIcon)
            itemView.itraImgSell.setImageResource(item.sellIcon)
            itemView.itraTxtDescription.text = item.description
            itemView.itraTxtDate.text = item.date
            itemView.itraTxtSellValue.text = item.sellValueDescription
            itemView.itraTxtBuyValue.text = item.buyValueDescription
        }
    }
}