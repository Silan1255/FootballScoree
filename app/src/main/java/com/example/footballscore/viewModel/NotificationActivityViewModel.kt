package com.example.footballscore.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballscore.model.notification.GetNotificationItem
import com.example.footballscore.model.skor.GetUserScoreItem
import com.example.footballscore.sevis.FutbolAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NotificationActivityViewModel : ViewModel() {
    private val futbolAPIServis = FutbolAPIServis()
    private val dispoosable = CompositeDisposable()

    fun refreshData() {
        dataNatifi()
    }

    val notifi = MutableLiveData<List<GetNotificationItem>>()
    private fun dataNatifi(){
        dispoosable.add(futbolAPIServis.getBildiri().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith
            (object : DisposableSingleObserver<List<GetNotificationItem>>() {

            override fun onSuccess(t: List<GetNotificationItem>) {
                notifi.value = t
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
        )
    }
}