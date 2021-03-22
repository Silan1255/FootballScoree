package com.example.footballscore.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.model.ResultResponse
import com.example.footballscore.model.maclar.SkorTahminEt
import com.example.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FootballFragmentViewModel : ViewModel() {
    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()

    val TahminEtResponse: MutableLiveData<ResultResponse> = MutableLiveData()
    fun newTakimRegister(body: SkorTahminEt) {
        dispoosable.add(
            futbolAPIServis.setNewTakim(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResultResponse>() {
                    override fun onSuccess(response: ResultResponse) {
                        TahminEtResponse.value = response
                        Log.i("good", response.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.i("hata", e.toString())
                    }
                })
        )
    }
}