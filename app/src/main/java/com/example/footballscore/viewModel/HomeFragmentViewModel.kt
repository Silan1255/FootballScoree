package com.example.footballscore.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.model.maclar.GetMaclar.GetMaclarItem
import com.example.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel : ViewModel() {

    private val futbolAPIServis = FutbolAPIServis()
    val takimler = MutableLiveData<List<GetMaclarItem>>()

    private val dispoosable = CompositeDisposable()


    fun refreshData() {
        verileriInternettenAl()
    }

    private fun verileriInternettenAl() {
        dispoosable.add(
            futbolAPIServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GetMaclarItem>>() {
                    override fun onSuccess(t: List<GetMaclarItem>) {
                        takimler.value = t
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}