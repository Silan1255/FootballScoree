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
import com.example.footballscore.adapter.TahminAdapter
import com.example.footballscore.databinding.FragmentFootballBinding
import com.example.footballscore.model.skor_tahmin.SkorTahminEt
import com.example.footballscore.viewModel.FootballFragmentViewModel
import com.example.footballscore.viewModel.ShredPreferenc
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_giris_ekrani.*
import kotlinx.android.synthetic.main.activity_kayit_ol.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet1.view.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet2.view.*
import kotlinx.android.synthetic.main.fragment_football.*

class FootballFragment : Fragment() {

    lateinit var binding: FragmentFootballBinding
    var prefHelper = ShredPreferenc()


    private lateinit var footballFragmentViewModel: FootballFragmentViewModel

    private val tahminAdapter = TahminAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_football, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        footballFragmentViewModel = ViewModelProviders.of(this).get(FootballFragmentViewModel::class.java)
        footballFragmentViewModel.refreshData()
        football_progressBar.visibility = View.VISIBLE

        futbol_tahmin_listesi.layoutManager = LinearLayoutManager(context)
        futbol_tahmin_listesi.adapter = tahminAdapter

        tahminAdapter.macTahminItemClıckLıstener = { macTahminAdi, macTahminAdi2, macTahminID, macBasladi ->

            if (macBasladi < 2.toString()) {
                BottomSheet(macTahminAdi, macTahminAdi2, macTahminID)
            }else if ( macBasladi.equals("İPT.")){
                BottomSheet2(macBasladi)
            }
            else if( macBasladi.equals("ERT.")){
                BottomSheet2(macBasladi)
            }
            else {
            BottomSheet1(macBasladi)
        }
        }
        observierLivePredictionData()
    }

    fun observierLivePredictionData() {
        footballFragmentViewModel.takimlerTahmin.observe(viewLifecycleOwner, Observer { TahminTakim ->
            TahminTakim?.let {
                futbol_tahmin_listesi.visibility = View.VISIBLE
                tahminAdapter.gecmisTahminleriGuncelle(TahminTakim.filter { it.tahminDakika != "MS" })
            }
            football_progressBar.visibility = View.GONE
        })
    }

    fun BottomSheet(tahminName: String, tahminName2: String, macTahminID: String) {
        val view: View = layoutInflater.inflate(R.layout.fragment_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        view.secound.setHint(tahminName2)
        view.first.setHint(tahminName)

        view.skor_tahmin1.setOnClickListener {
            val FirstTahmin: String = view.first_tamin__et.text.toString().trim()
            val SecoundTahmin: String = view.secound_tahmin_et.text.toString().trim()
            if (FirstTahmin.isEmpty() || SecoundTahmin.isEmpty()) {
                Toast.makeText(context, "Lütfen skor tahmini giriniz.", Toast.LENGTH_LONG).show()
            } else {
                footballFragmentViewModel.newTakimTahmin(SkorTahminEt(prefHelper.getValue(requireContext(),
                    "userId"), (FirstTahmin + " - " + SecoundTahmin), macTahminID))
                footballFragmentViewModel.TahminEtResponse.observe(viewLifecycleOwner, Observer {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                })
            }
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    fun BottomSheet1(tahminName1: String) {
        val view: View = layoutInflater.inflate(R.layout.fragment_bottom_sheet1, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        view.tahmin_yapamazsiniz.text = tahminName1
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    fun BottomSheet2(tahminName2: String) {
        val view: View = layoutInflater.inflate(R.layout.fragment_bottom_sheet2, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        view.tahmin_iptal2.text = tahminName2
        if (tahminName2.equals("ERT.")){
            view.tahmin_iptal.text ="Maç Ertelendi Tahmin Yapamazsınız !!!"
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}


