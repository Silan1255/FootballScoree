package com.example.footballscore.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.footballscore.R
import com.example.footballscore.pages.LoginScreen
import com.example.footballscore.viewModel.ProfilFragmentViewModel
import com.example.footballscore.viewModel.ShredPreferenc
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
        profilFragmentViewModel.getUserData(prefHelper.getValue(requireContext(), "userId"))
        observeLiveData()

        txt_bugunki_tahminlerim.setOnClickListener {
            var intent = Intent(context, TahminlerVeYorumlar::class.java)
            intent.putExtra("Key", "todayGuess")
            startActivity(intent)
        }
        txt_onceki_tahminlerim.setOnClickListener {
            var intent = Intent(context, TahminlerVeYorumlar::class.java)
            intent.putExtra("Key", "afterGuess")
            startActivity(intent)
        }
        txt_yorumlarim.setOnClickListener {
            var intent = Intent(context, NotificationActivity::class.java)
            startActivity(intent)

        }
        ttt_cikis.setOnClickListener {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle("Çıkış Yapmak İstediğinize Emin misiniz?")
            alertDialog.setPositiveButton("Evet") { dialog, id ->
                activity?.finish()
            }
            alertDialog.setNegativeButton("Hayır") { dialog, id ->
                 Intent(context, ProfilFragment::class.java)

            }
            val alert = alertDialog.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()

        }
    }

    fun observeLiveData() {
        profilFragmentViewModel.kullaniciAdi.observe(viewLifecycleOwner, Observer { kullaniciAdin ->
            kullaniciAdin?.let {
                profilUserName.visibility = View.VISIBLE
                profilUserName.text = "Hoşgeldin " + it[0].userName
            }
        })
    }
}