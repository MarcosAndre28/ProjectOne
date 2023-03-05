package com.example.projectone.adapter


import android.graphics.drawable.PictureDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.example.kotlindls.R
import com.example.projectone.db.model.TickerModelDB



class TickerAdapter(private val ticker: List<TickerModelDB>) :
    RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item, parent, false)
        return TickerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ticker.size
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        val list = ticker[position]
        holder.name.text = list.stock
        holder.close.text = list.close.toString()

        val options = RequestOptions().format(DecodeFormat.PREFER_ARGB_8888)

        Glide.with(holder.logo.context)
            .`as`(PictureDrawable::class.java)
            .decode(Class.forName("com.example.projectone.utils.PictureDrawableDecoder"))
            .load(list.logo).into(holder.logo)


    }

    inner class TickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txt_name_crypto)
        val close = itemView.findViewById<TextView>(R.id.txt_value_crypto)
        val logo = itemView.findViewById<ImageView>(R.id.txt_icon_crypto)
    }
}