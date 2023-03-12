package com.example.projectone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindls.R
import com.example.projectone.db.model.CryptoModelDB

class CryptoAdapter(private val cryptos: List<CryptoModelDB>) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_ticker_item, parent, false)
        return CryptoViewHolder(view)
    }

    override fun getItemCount(): Int = cryptos.size

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val list = cryptos[position]
        holder.coin.text = list.coin
        holder.regularMarketPrice.text = list.regularMarketDayRange
//        holder.coinImageUrl.text = list.coinImageURL
    }

    inner class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coin = itemView.findViewById<TextView>(R.id.txt_name_title)
        val regularMarketPrice = itemView.findViewById<TextView>(R.id.txt_value_valor)
        val coinImageUrl = itemView.findViewById<TextView>(R.id.txt_icon_crypto)
    }
}