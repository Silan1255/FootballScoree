package com.example.footballscore.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.adapter.SkorSonucAdapter
import com.example.footballscore.databinding.ActivityTahminlerVeYorumlarBinding
import com.example.footballscore.model.skor_tahmin.SkorSonucItem
import com.example.footballscore.viewModel.ShredPreferenc
import com.example.footballscore.viewModel.TahminVeYorumlarViewModel
import kotlinx.android.synthetic.main.activity_tahminler_ve_yorumlar.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TahminlerVeYorumlar : AppCompatActivity() {
    private val skorSonucAdapter = SkorSonucAdapter(arrayListOf())
    private lateinit var tahminVeYorumlarViewModel: TahminVeYorumlarViewModel
    var prefHelper = ShredPreferenc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTahminlerVeYorumlarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("Key")?.let {
             if (it.contains("afterGuess"))
            {
                binding.titleCommentPage.text = "Önceki Tahminlerim"
            }else if (it.contains("todayGuess"))
            {
                binding.titleCommentPage.text = "Bugünkü Tahminlerim"
            }
        }
        tahminVeYorumlarViewModel = ViewModelProviders.of(this).get(TahminVeYorumlarViewModel::class.java)
        intent.getStringExtra("userId")?.let {
            tahminVeYorumlarViewModel.GetSkorData(it)
        }?:run{
            tahminVeYorumlarViewModel.GetSkorData(prefHelper.getValue(this, "userId"))
        }


        rcy_profil.layoutManager = LinearLayoutManager(applicationContext)
        rcy_profil.adapter = skorSonucAdapter
        observierLivePredictionData()
    }

    fun observierLivePredictionData() {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val currentDateandTime = currentDate.format(Date())
        tahminVeYorumlarViewModel.TahminSkor.observe(this, Observer { TahminTakim ->
            TahminTakim?.let {
                intent.getStringExtra("Key")?.let { key->
                    if (key.contains("todayGuess")) {
                        skorSonucAdapter.gecmisSkorlariGuncelle(it.filter { getTime(it.userGuessDateTime) == currentDateandTime}, false)
                    }else if (key.contains("afterGuess")){
                        skorSonucAdapter.gecmisSkorlariGuncelle(it.filter { getTime(it.userGuessDateTime) != currentDateandTime}, true)
                    }
                }
            }
        })
    }
    fun getTime(date: String) : String {
        val outputFormat: DateFormat = SimpleDateFormat("yyyy-dd-mm", Locale.US)
        val inputText = date
        val date: Date? = outputFormat.parse(inputText)
        val outputText = outputFormat.format(date)
        return outputText
    }
}



