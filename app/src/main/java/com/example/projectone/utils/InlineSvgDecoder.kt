package com.example.projectone.utils

import android.graphics.Picture
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.caverock.androidsvg.SVG
import java.io.IOException
import java.io.InputStream

class PictureDrawableDecoder : ResourceDecoder<SVG, PictureDrawable> {
    override fun handles(source: SVG, options: Options): Boolean {
        // retorna verdadeiro se este decodificador puder manipular o recurso SVG de entrada.
        return true
    }

    @Throws(IOException::class)
    override fun decode(source: SVG, width: Int, height: Int, options: Options): Resource<PictureDrawable>? {
        val picture = source.renderToPicture()
        val drawable = PictureDrawable(picture)
        return SimpleResource(drawable)
    }
}