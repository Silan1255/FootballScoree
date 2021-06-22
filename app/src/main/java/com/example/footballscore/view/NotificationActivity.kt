package com.example.footballscore.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.R
import com.example.footballscore.adapter.NotificationAdapter
import com.example.footballscore.adapter.SiralamaAdapter
import com.example.footballscore.viewModel.NotificationActivityViewModel
import com.example.footballscore.viewModel.SiralamaFragmentViewModel
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_siralama.*

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
