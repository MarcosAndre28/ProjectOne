package com.example.projectone.utils

import android.graphics.drawable.PictureDrawable
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class SvgLoaderUtil {

    companion object {
        fun loadSvgFromUrl(imageView: ImageView, url: String) {
            SvgLoadTask(imageView).execute(url)
        }
    }

    private class SvgLoadTask(private val imageView: ImageView) :
        AsyncTask<String, Void, SVG>() {

        override fun doInBackground(vararg params: String?): SVG? {
            val url = params[0] ?: return null
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream: InputStream = connection.inputStream
            return try {
                SVG.getFromInputStream(inputStream)
            } catch (e: SVGParseException) {
                throw RuntimeException("Error parsing SVG", e)
            } finally {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                connection.disconnect()
            }
        }

        override fun onPostExecute(result: SVG?) {
            super.onPostExecute(result)
            result?.let {
                imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                imageView.setImageDrawable(PictureDrawable(it.renderToPicture()))
            }
        }
    }
}
