package com.example.footballscore.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.model.maclar.GetMaclar
import com.example.footballscore.model.skor.GetUserScoreItem
import com.example.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SiralamaFragmentViewModel : ViewModel() {
    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()

    fun refreshData() {
       dataskor()
    }

    val skor = MutableLiveData<List<GetUserScoreItem>>()
    private fun dataskor() {
        dispoosable.add(futbolAPIServis.getSkor().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith
            (object : DisposableSingleObserver<List<GetUserScoreItem>>() {
            override fun onSuccess(t: List<GetUserScoreItem>) {
                skor.value = t
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
        )
    }
}