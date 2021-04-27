package com.example.footballscore.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import kotlinx.android.synthetic.main.fragment_football.*

class FootballFragment : Fragment() {

    lateinit var Tahminbinding: FragmentFootballBinding

    private lateinit var footballFragmentViewModel: FootballFragmentViewModel
    var FirstTahmin: String? = ""
    var SecoundTahmin: String? = ""

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

        tahminAdapter.macTahminItemClıckLıstener = { macTahminAdi, macTahminAdi2, macTahminID ->
            Toast.makeText(requireContext(), macTahminAdi + "-" + macTahminAdi2 + "-" + macTahminID, Toast.LENGTH_LONG).show()
            BottomSheet(macTahminAdi, macTahminAdi2)
        }
        observierLivePredictionData()
      /*  Tahminbinding.apply {
            skor_tahmin.setOnClickListener {
                val FirstTahmin: String = first_tahmin.text.toString().trim()
                val SecoundTahmin: String = secound_tahmin.text.toString().trim()
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(FirstTahmin, SecoundTahmin)
                    .addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                footballFragmentViewModel.newUserTakim(SkorTahminEt(FirstTahmin, SecoundTahmin))
                                Toast.makeText(context, "Tahmin işlemini  başarıyla tamamladınız.", Toast.LENGTH_LONG).show()
                               val intent= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            } else {
                                Toast.makeText(context, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                            }
                        })
            }
        }

       */
    }




    fun observierLivePredictionData() {
        footballFragmentViewModel.takimlerTahmin.observe(viewLifecycleOwner, Observer { TahminTakim ->
            TahminTakim?.let {
                futbol_tahmin_listesi.visibility = View.VISIBLE
                tahminAdapter.gecmisTahminleriGuncelle(TahminTakim)
            }
        })
    }

    fun BottomSheet(tahminName: String, tahminName2: String) {
        val view: View = layoutInflater.inflate(R.layout.fragment_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        view.tahmin_first.text = tahminName
        view.tahmin_secound.text = tahminName2
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }


}


