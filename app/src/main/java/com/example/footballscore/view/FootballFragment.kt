package com.example.footballscore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.footballscore.R
import com.example.footballscore.model.maclar.SkorTahminEt
import com.example.footballscore.viewModel.FootballFragmentViewModel
import com.example.footballscore.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_football.*

class FootballFragment: Fragment(){

    private lateinit var footballFragmentViewModel: FootballFragmentViewModel

        var TahminFirst: String?=""
        var TahminSecond: String?=""
        var TahminSonuc: Int?= 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_football, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        footballFragmentViewModel = ViewModelProviders.of(this).get(FootballFragmentViewModel::class.java)

        TahminFirst = tahminFirst.text.toString()
        TahminSecond = tahminSecond.text.toString()

        observeLiveData()
//Şimdilik böyle kalsın bakacağım buna derdi neymiş

        sonucuGonder.setOnClickListener {
            footballFragmentViewModel.newTakimRegister(SkorTahminEt(tahminFirst.text.toString(), tahminSecond.text.toString(), macSonucu.text.toString()))
        }
    }
        fun observeLiveData() {
            footballFragmentViewModel.TahminEtResponse.observe(viewLifecycleOwner, Observer {
              Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            })
        }
}

