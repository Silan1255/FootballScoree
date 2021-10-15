package com.tahmin.footballscore.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tahmin.footballscore.model.ResultResponse
import com.tahmin.footballscore.model.skor_tahmin.GetTahminMaclar.GetTahminMaclarItem
import com.tahmin.footballscore.model.skor_tahmin.ScoreGuess
import com.tahmin.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class FootballFragmentViewModel : ViewModel() {

    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()

    fun refreshData() {
        GetData()
    }

    val takimlerTahmin = MutableLiveData<List<GetTahminMaclarItem>>()
    val TahminEtResponse: MutableLiveData<ResultResponse> = MutableLiveData()

    private fun GetData() {
        dispoosable.add(
            futbolAPIServis.getCanliMac()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GetTahminMaclarItem>>() {
                    override fun onSuccess(t: List<GetTahminMaclarItem>) {
                        takimlerTahmin.value = t
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    fun newTakimTahmin(body: ScoreGuess) {
        dispoosable.add(
            futbolAPIServis.setNewTakim(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResultResponse>() {
                    override fun onSuccess(response: ResultResponse) {
                        TahminEtResponse.value = response
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}
