package com.tahmin.footballscore.view

import android.content.ContentValues.TAG
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.tahmin.footballscore.R
import com.tahmin.footballscore.adapter.PredictionAdapter
import com.tahmin.footballscore.databinding.FragmentFootballBinding
import com.tahmin.footballscore.model.skor_tahmin.ScoreGuess
import com.tahmin.footballscore.viewModel.FootballFragmentViewModel
import com.tahmin.footballscore.viewModel.ShredPreferenc
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet1.view.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet2.view.*
import kotlinx.android.synthetic.main.fragment_football.*

class FootballFragment : Fragment() {

    var prefHelper = ShredPreferenc()
    private var mInterstitialAd: InterstitialAd? = null
    private var isVisibleCount: Int = Hawk.get("isShowCounter",1)

    private lateinit var footballFragmentViewModel: FootballFragmentViewModel

    private val tahminAdapter = PredictionAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_football, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdmobBanner()
        footballFragmentViewModel = ViewModelProviders.of(this).get(FootballFragmentViewModel::class.java)
        footballFragmentViewModel.refreshData()
        football_progressBar.visibility = View.VISIBLE

        footballFragmentViewModel.TahminEtResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.status.equals("True")){
                    if (Hawk.get("isShowCounter",1) == 2){
                        Hawk.delete("isShowCounter")
                    }
                    if (Hawk.get("isShowCounter",1) == 2){
                        Log.i("Count",Hawk.get("isShowCounter",1).toString())
                        interstitialAddAndLoad()
                    }
                    Hawk.put("isShowCounter", isVisibleCount++)
                }
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        })

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

        view.apply {
            secound.setHint(tahminName2)
            secound.hintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
            first.setHint(tahminName)
            first.hintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
        }

        view.skor_tahmin1.setOnClickListener {
            val FirstTahmin: String = view.first_tamin__et.text.toString().trim()
            val SecoundTahmin: String = view.secound_tahmin_et.text.toString().trim()
            if (FirstTahmin.isEmpty() || SecoundTahmin.isEmpty()) {
                Toast.makeText(context, "Lütfen skor tahmini giriniz.", Toast.LENGTH_LONG).show()
            } else {
                footballFragmentViewModel.newTakimTahmin(ScoreGuess(prefHelper.getValue(requireContext(), "userId"), (FirstTahmin + " - " + SecoundTahmin), macTahminID))
                bottomSheetDialog.dismiss()
            }
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    override fun onStart() {
        super.onStart()
        if (Hawk.get("isShowCounter",0) >= 2){
            Hawk.delete("isShowCounter")
        }
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
    //Banner içi
    fun initAdmobBanner(){
        val adViewInit = AdView(requireContext())
        var adSize = AdSize(150,150)
        with(adViewInit){
            adSize = AdSize.BANNER
            adUnitId = "ca-app-pub-4148624023284077/2943809337"
        }
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
    //Geçiş reklamı için
    fun interstitialAddAndLoad(){
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireContext(),"ca-app-pub-4148624023284077/5765908638", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError.message)
                mInterstitialAd = null
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.show(requireActivity())
            }
        })
    }
}


