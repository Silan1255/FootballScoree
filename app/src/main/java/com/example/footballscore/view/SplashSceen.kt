package com.example.footballscore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.footballscore.R
import com.example.footballscore.pages.LoginScreen

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
        },3000)













    }
}