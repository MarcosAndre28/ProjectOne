package com.example.projectone

import android.app.Application
import com.example.kotlindls.BuildConfig
import timber.log.Timber

class ProjetcOneApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}