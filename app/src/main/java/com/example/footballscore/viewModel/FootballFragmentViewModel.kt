package com.example.footballscore.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.model.skor_tahmin.GetTahminMaclar.GetTahminMaclarItem
import com.example.footballscore.sevis.FutbolAPIServis
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

    private fun GetData() {
        dispoosable.add(
            futbolAPIServis.getCanliMac()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GetTahminMaclarItem>>() {
                    override fun onSuccess(t: List<GetTahminMaclarItem>) {
                        takimlerTahmin.value = t.filter { macTahminSkorItem ->
                            macTahminSkorItem.tahminMacSonucu.trim().length <2

                        }
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}
