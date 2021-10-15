package com.tahmin.footballscore.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.tahmin.footballscore.R
import com.tahmin.footballscore.pages.LoginScreen

@Suppress("DEPRECATION")
class SplashSceen : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_sceen)
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}