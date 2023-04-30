package com.example.projectone.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindls.R
import com.example.projectone.db.model.FavoriteTickerModelDB
import com.example.projectone.utils.SvgLoaderUtil


class FavoriteTickerAdapter(private val ticker: List<FavoriteTickerModelDB>) :
    RecyclerView.Adapter<FavoriteTickerAdapter.FavoriteTickerViewHolder>() {

    private lateinit var myListener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_ticker_item, parent, false)
        return FavoriteTickerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ticker.size
    }

    override fun onBindViewHolder(holder: FavoriteTickerViewHolder, position: Int) {
        val list = ticker[position]
        holder.name.text = list.stock
        holder.close.text = list.close.toString()
        SvgLoaderUtil.loadSvgFromUrl(holder.logo, list.logo)

        holder.btn.setOnClickListener {
            val position = getItemId(position)
            ticker.get(position.toInt())

        }
    }

     inner class FavoriteTickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txt_name_title)
        val close = itemView.findViewById<TextView>(R.id.txt_value_valor)
        var logo = itemView.findViewById<ImageView>(R.id.txt_icon_crypto)
        val btn = itemView.findViewById<Button>(R.id.btn_favorite)
    }

}