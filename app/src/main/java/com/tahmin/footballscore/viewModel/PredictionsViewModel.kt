package com.tahmin.footballscore.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tahmin.footballscore.model.skor_tahmin.SkorSonucItem
import com.tahmin.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PredictionsViewModel: ViewModel() {

    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()
    val TahminSkor = MutableLiveData<List<SkorSonucItem>>()

    fun GetSkorData(userId:String) {
        dispoosable.add(
            futbolAPIServis.getMacGuess(userId )
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<SkorSonucItem>>() {
                    override fun onSuccess(t: List<SkorSonucItem>) {
                        TahminSkor.value = t
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}