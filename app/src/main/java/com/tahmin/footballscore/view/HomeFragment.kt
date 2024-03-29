package com.tahmin.footballscore.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahmin.footballscore.adapter.FootballAdapter
import com.tahmin.footballscore.R
import com.tahmin.footballscore.adapter.NotificationAdapter
import com.tahmin.footballscore.model.guess.UserGuessCheck
import com.tahmin.footballscore.viewModel.HomeFragmentViewModel
import com.tahmin.footballscore.viewModel.NotificationActivityViewModel
import com.tahmin.footballscore.viewModel.ShredPreferenc
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val notificationAdapter= NotificationAdapter(arrayListOf())
    private lateinit var notificationActivityViewModel: NotificationActivityViewModel
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    var prefHelper = ShredPreferenc()
    private var isCheck: Boolean = false

    private val futbolAdapter = FootballAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_notifi.setOnClickListener {
            var intent = Intent(context, NotificationActivity::class.java)
            startActivity(intent)
        }
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        homeFragmentViewModel.refreshData()

        notificationActivityViewModel = ViewModelProviders.of(this).get(NotificationActivityViewModel::class.java)
        notificationActivityViewModel.refreshData()

        home_progressBar.visibility = View.VISIBLE
        homeFragmentViewModel.checkGuess(UserGuessCheck(prefHelper.getValue(requireContext(), "userId")))

        futbol_listesi.layoutManager = LinearLayoutManager(context)
        futbol_listesi.adapter = futbolAdapter
        observeLiveData()
    }

    fun observeLiveData() {

        homeFragmentViewModel.checkGuessResult.observe(viewLifecycleOwner, Observer {
            if (it.status.equals("True")){
                animationView.visibility = View.VISIBLE
                Handler().postDelayed({
                    animationView.visibility = View.GONE
                },3000)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })

        notificationActivityViewModel.notifi.observe(viewLifecycleOwner, Observer { notification ->
            notification?.let {
                cart_badge.text = it.size.toString()
                notificationAdapter.BildiriyiGüncelle(notification)
            }
        })

        homeFragmentViewModel.takimler.observe(viewLifecycleOwner, Observer { Takimler ->
            Takimler?.let {
                futbol_listesi.visibility = View.VISIBLE
                futbolAdapter.gecmisMacSkorlariniGüncelle(Takimler)
            }
            home_progressBar.visibility = View.GONE
        })
    }
}