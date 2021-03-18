package com.example.footballscore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.R
import com.example.footballscore.adapter.FutbolAdapter
import com.example.footballscore.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    private val futbolAdapter = FutbolAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        homeFragmentViewModel.refreshData()

        futbol_listesi.layoutManager= LinearLayoutManager(context)
        futbol_listesi.adapter=futbolAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        Toast.makeText(requireContext(), "Observable", Toast.LENGTH_LONG).show()
        homeFragmentViewModel.takimler.observe(viewLifecycleOwner, Observer { Takimler ->
           Takimler?.let {
               futbol_listesi.visibility= View.VISIBLE
               futbolAdapter.gecmisMacSkorlariniGüncelle(Takimler)
           }

        })

    }
}