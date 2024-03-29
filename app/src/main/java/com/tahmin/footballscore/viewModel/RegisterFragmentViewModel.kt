package com.tahmin.footballscore.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tahmin.footballscore.model.ResultResponse
import com.tahmin.footballscore.model.user.RegisterUserItem
import com.tahmin.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RegisterFragmentViewModel : ViewModel() {

    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()
    val kayitOlResponse: MutableLiveData<ResultResponse> = MutableLiveData()


    fun newUserRegister(body: RegisterUserItem) {
        dispoosable.add(
            futbolAPIServis.setNewUser(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResultResponse>() {
                    override fun onSuccess(response: ResultResponse) {
                        kayitOlResponse.value = response
                    }

                    override fun onError(e: Throwable) {
                        Log.i("hata", e.toString())
                    }
                })
        )
    }
}