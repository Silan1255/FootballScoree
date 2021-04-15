package com.example.footballscore.view

import android.content.Context
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
import com.example.footballscore.adapter.TahminAdapter
import com.example.footballscore.viewModel.FootballFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import kotlinx.android.synthetic.main.fragment_football.*

class FootballFragment : Fragment() {

    private lateinit var footballFragmentViewModel: FootballFragmentViewModel

    private val tahminAdapter = TahminAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_football, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        footballFragmentViewModel = ViewModelProviders.of(this).get(FootballFragmentViewModel::class.java)
        footballFragmentViewModel.refreshData()

        futbol_tahmin_listesi.layoutManager = LinearLayoutManager(context)
        futbol_tahmin_listesi.adapter = tahminAdapter

        tahminAdapter.macTahminItemClıckLıstener = { macTahminAdi,macTahminAdi2, macTahminID ->
            Toast.makeText(requireContext(), macTahminAdi + "-" + macTahminAdi2 + "-" + macTahminID, Toast.LENGTH_LONG).show()
            BottomSheet(macTahminAdi,macTahminAdi2)
        }
        observierLivePredictionData()
    }

    fun observierLivePredictionData() {
        footballFragmentViewModel.takimlerTahmin.observe(viewLifecycleOwner, Observer { TahminTakim ->
            TahminTakim?.let {
                futbol_tahmin_listesi.visibility = View.VISIBLE
                tahminAdapter.gecmisTahminleriGuncelle(TahminTakim)
            }
        })
    }
     fun BottomSheet(tahminName : String, tahminName2: String){
        val view: View = layoutInflater.inflate(R.layout.fragment_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
         view.tahmin_first.text = tahminName
         view.tahmin_secound.text = tahminName2
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }



    //Bottom sheet ıçın bır method yazdım tık
}

