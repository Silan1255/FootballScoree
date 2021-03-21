package com.example.footballscore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballscore.R
import com.example.footballscore.viewModel.FootballFragmentViewModel
import com.example.footballscore.viewModel.KayitOlFragmentViewModel

class FootballFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_football,container,false)

        lateinit var footballFragmentViewModel: FootballFragmentViewModel


    }
}