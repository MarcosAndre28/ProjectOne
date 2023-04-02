package com.example.projectone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindls.R
import com.example.projectone.db.model.CryptoAvailableModelDB
import com.example.projectone.db.model.CryptoModelDB

class CryptoAdapter : ListAdapter<CryptoAvailableModelDB, CryptoViewHolder>(CryptoItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crypto_available, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val cryptoName = itemView.findViewById<TextView>(R.id.tvCoins)
    fun bind(cryptoItem: CryptoAvailableModelDB) {
        cryptoName.text = cryptoItem.coins.toString()
    }
}

class CryptoItemDiffCallback : DiffUtil.ItemCallback<CryptoAvailableModelDB>() {
    override fun areItemsTheSame(oldItem: CryptoAvailableModelDB, newItem: CryptoAvailableModelDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CryptoAvailableModelDB, newItem: CryptoAvailableModelDB): Boolean {
        return oldItem == newItem
    }
}
