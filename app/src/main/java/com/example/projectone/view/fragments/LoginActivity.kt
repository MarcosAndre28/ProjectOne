package com.example.projectone.view.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.kotlindls.databinding.SplashActivityBinding
import com.example.projectone.view.MainActivity
import com.example.projectone.base.BaseActivity

class LoginActivity : BaseActivity<SplashActivityBinding>(){

    override fun getViewBinding() = SplashActivityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
           startActivity(Intent(this, MainActivity::class.java))
        },5000)
    }

}