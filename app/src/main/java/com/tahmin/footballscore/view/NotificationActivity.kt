package com.tahmin.footballscore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahmin.footballscore.R
import com.tahmin.footballscore.adapter.NotificationAdapter
import com.tahmin.footballscore.viewModel.NotificationActivityViewModel
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    private val notificationAdapter=NotificationAdapter(arrayListOf())
    private lateinit var notificationActivityViewModel: NotificationActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        notificationActivityViewModel = ViewModelProviders.of(this).get(NotificationActivityViewModel::class.java)
        notificationActivityViewModel.refreshData()

        rcy_notification.layoutManager = LinearLayoutManager(this)
        rcy_notification.adapter = notificationAdapter


        observeLiveData()
    }
    fun observeLiveData() {
        notificationActivityViewModel.notifi.observe(this, Observer { notification ->
            notification?.let {

                rcy_notification.visibility = View.VISIBLE
                notificationAdapter.BildiriyiGüncelle(notification)
            }
        })
    }
}
