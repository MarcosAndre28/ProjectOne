package com.example.projectone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindls.R
import com.example.projectone.data.models.CryptoModel

class CryptoAdapter(val cryptos: MutableList<CryptoModel>) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item, parent, false)
        return CryptoViewHolder(view)
    }

    override fun getItemCount(): Int = cryptos.size

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.coin.text = cryptos[position].coin
        holder.regularMarketPrice.text = cryptos[position].regularMarketDayRange
        holder.coinImageUrl.text = cryptos[position].coinImageURL
    }

    inner class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coin = itemView.findViewById<TextView>(R.id.txt_name_crypto)
        val regularMarketPrice = itemView.findViewById<TextView>(R.id.txt_value_crypto)
        val coinImageUrl = itemView.findViewById<TextView>(R.id.txt_icon_crypto)
    }
}