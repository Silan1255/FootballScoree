package com.tahmin.footballscore.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahmin.footballscore.adapter.ScoreConclusionAdapter
import com.tahmin.footballscore.databinding.ActivityPredictionsBinding
import com.tahmin.footballscore.viewModel.ShredPreferenc
import com.tahmin.footballscore.viewModel.PredictionsViewModel
import kotlinx.android.synthetic.main.activity_predictions.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PredictionsFragment : AppCompatActivity() {
    private val skorSonucAdapter = ScoreConclusionAdapter(arrayListOf())
    private lateinit var predictionsViewModel: PredictionsViewModel
    var prefHelper = ShredPreferenc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPredictionsBinding.inflate(layoutInflater)
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
        predictionsViewModel = ViewModelProviders.of(this).get(PredictionsViewModel::class.java)
        intent.getStringExtra("userId")?.let {
            predictionsViewModel.GetSkorData(it)
        }?:run{
            predictionsViewModel.GetSkorData(prefHelper.getValue(this, "userId"))
        }


        rcy_profil.layoutManager = LinearLayoutManager(applicationContext)
        rcy_profil.adapter = skorSonucAdapter
        observierLivePredictionData()
    }

    fun observierLivePredictionData() {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val currentDateandTime = currentDate.format(Date())
        predictionsViewModel.TahminSkor.observe(this, Observer { TahminTakim ->
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



