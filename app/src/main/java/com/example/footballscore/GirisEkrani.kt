package com.example.footballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputBinding
import android.widget.Toast
import com.example.footballscore.databinding.ActivityGirisEkraniBinding
import kotlinx.android.synthetic.main.activity_giris_ekrani.*

class GirisEkrani : AppCompatActivity() {

    lateinit var binding: ActivityGirisEkraniBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGirisEkraniBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        binding.btnGirisYapiniz.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        binding.txtKayitYapmadiysaniz.setOnClickListener {
            intent = Intent(applicationContext, KayitOl::class.java)
            startActivity(intent)
        }
    }
}