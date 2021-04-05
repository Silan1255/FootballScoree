package com.example.footballscore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.footballscore.R
import com.example.footballscore.adapter.FutbolAdapter
import com.example.footballscore.viewModel.ProfilFragmentViewModel
import com.example.footballscore.viewModel.ShredPreferenc
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {

    private lateinit var profilFragmentViewModel: ProfilFragmentViewModel
    var prefHelper = ShredPreferenc()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilFragmentViewModel = ViewModelProviders.of(this).get(ProfilFragmentViewModel::class.java)
        prefHelper.getValue(requireContext(),"userId").toIntOrNull()?.let { profilFragmentViewModel.getUserData(userId = it) }


        observeLiveData()

    }

    fun observeLiveData() {
        profilFragmentViewModel.kullaniciAdi.observe(viewLifecycleOwner, Observer { kullaniciAdin ->
            kullaniciAdin?.let {
                profilUserName.visibility = View.VISIBLE
                profilUserName.text = "Hoşgeldin "+ it[0].userName
            }
        })
    }
}