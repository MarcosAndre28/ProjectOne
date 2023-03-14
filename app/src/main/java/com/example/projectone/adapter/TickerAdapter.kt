package com.example.projectone.adapter


import android.graphics.drawable.PictureDrawable
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.example.kotlindls.R
import com.example.projectone.db.model.TickerModelDB
import com.example.projectone.utils.SvgLoaderUtil
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


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
        SvgLoaderUtil.loadSvgFromUrl(holder.logo, list.logo)
    }

    inner class TickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txt_name_title)
        val close = itemView.findViewById<TextView>(R.id.txt_value_valor)
        var logo = itemView.findViewById<ImageView>(R.id.txt_icon_crypto)
    }
}