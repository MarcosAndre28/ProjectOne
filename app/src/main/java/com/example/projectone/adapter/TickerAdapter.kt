package com.example.projectone.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindls.R
import com.example.projectone.db.model.TickerModelDB


class TickerAdapter(private val ticker: List<TickerModelDB>) :
    RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_ticker_item, parent, false)
        return TickerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ticker.size
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        val list = ticker[position]
        holder.name.text = list.stock
        holder.close.text = list.close.toString()

//        val svg = SVG.getFromString(list.logo)
//        Log.d("SVG content", list.logo)
//        val picture = svg.renderToPicture()
//        val drawable = PictureDrawable(picture)
//
//        holder.logo.setImageDrawable(drawable)
    }

    inner class TickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txt_name_title)
        val close = itemView.findViewById<TextView>(R.id.txt_value_valor)
        var logo = itemView.findViewById<ImageView>(R.id.txt_icon_crypto)
    }
}