package com.example.footballscore

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.footballscore.databinding.ActivityKayitOlBinding

class KayitOl : AppCompatActivity() {
    lateinit var binding: ActivityKayitOlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityKayitOlBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        binding.txtKayitYatiysaniz.setOnClickListener {
            intent= Intent(applicationContext, GirisEkrani::class.java)
            startActivity(intent)
        }
    }
}