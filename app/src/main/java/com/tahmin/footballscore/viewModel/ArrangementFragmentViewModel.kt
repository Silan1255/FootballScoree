package com.tahmin.footballscore.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tahmin.footballscore.model.skor.GetUserScoreItem
import com.tahmin.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ArrangementFragmentViewModel : ViewModel() {
    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()

    fun refreshData() {
       dataskor()
    }

    val skor = MutableLiveData<List<GetUserScoreItem>>()
    val haveData = MutableLiveData<Boolean>()
    private fun dataskor() {
        dispoosable.add(futbolAPIServis.getSkor().
        subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith
            (object : DisposableSingleObserver<List<GetUserScoreItem>>() {
            override fun onSuccess(t: List<GetUserScoreItem>) {
                skor.value = t
                haveData.value = true
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
                haveData.value = false
            }
        })
        )
    }
}