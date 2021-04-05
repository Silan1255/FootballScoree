package com.example.footballscore.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.model.user.GetUser.GetUserItem
import com.example.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProfilFragmentViewModel : ViewModel() {

    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()

    val kullaniciAdi = MutableLiveData<List<GetUserItem>>()

    fun getUserData(userId : Int) {
        dispoosable.add(
            futbolAPIServis.getUser(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribeWith(object : DisposableSingleObserver<List<GetUserItem>>() {

                    override fun onSuccess(t:List<GetUserItem>) {
                        Log.i("json", t.toString())
                        kullaniciAdi.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}