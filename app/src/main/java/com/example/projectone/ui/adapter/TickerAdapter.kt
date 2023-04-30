package com.example.projectone.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindls.R
import com.example.projectone.db.model.TickerModelDB
import com.example.projectone.utils.SvgLoaderUtil

class TickerAdapter(private val ticker: MutableList<TickerModelDB>,private val onFavoriteClick: (TickerModelDB, Boolean) -> Unit) :
    RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_ticker_item, parent, false)
        return TickerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ticker.size
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        val tickerItem = ticker[position]
        holder.name.text = tickerItem.stock
        holder.close.text = tickerItem.close.toString()
        SvgLoaderUtil.loadSvgFromUrl(holder.logo, tickerItem.logo)

        holder.favorite.apply {
            if (tickerItem.isFavorite){

                setImageResource(R.drawable.ic_favorite)
            }
            else{
                setImageResource(R.drawable.ic_not_favorite)
            }
        }

        holder.favorite.setOnClickListener {
            val newIsFavorite = !tickerItem.isFavorite
            onFavoriteClick(tickerItem, newIsFavorite)
            updateFavoriteStatus(tickerItem, newIsFavorite)
        }
    }

    inner class TickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txt_name_title)
        val close = itemView.findViewById<TextView>(R.id.txt_value_valor)
        val logo = itemView.findViewById<ImageView>(R.id.txt_icon_crypto)
        val favorite = itemView.findViewById<ImageView>(R.id.btn_favorite)
    }

    fun removeItem(tickerItem: TickerModelDB) {
        val position = ticker.indexOf(tickerItem)
        ticker.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateList(newTickerList: MutableList<TickerModelDB>) {
        ticker.clear()
        ticker.addAll(newTickerList)
        notifyDataSetChanged()
    }

    fun updateFavoriteStatus(tickerItem: TickerModelDB, isFavorite: Boolean) {
        val position = ticker.indexOf(tickerItem)
        ticker[position].isFavorite = isFavorite
        notifyItemChanged(position)
    }
}

