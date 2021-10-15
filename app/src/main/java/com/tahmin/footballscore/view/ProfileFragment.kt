package com.tahmin.footballscore.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tahmin.footballscore.R
import com.tahmin.footballscore.viewModel.ProfileFragmentViewModel
import com.tahmin.footballscore.viewModel.ShredPreferenc
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var profileFragmentViewModel: ProfileFragmentViewModel
    var prefHelper = ShredPreferenc()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileFragmentViewModel = ViewModelProviders.of(this).get(ProfileFragmentViewModel::class.java)
        profileFragmentViewModel.getUserData(prefHelper.getValue(requireContext(), "userId"))
        observeLiveData()

        txt_bugunki_tahminlerim.setOnClickListener {
            var intent = Intent(context, PredictionsFragment::class.java)
            intent.putExtra("Key", "todayGuess")
            startActivity(intent)
        }
        txt_onceki_tahminlerim.setOnClickListener {
            var intent = Intent(context, PredictionsFragment::class.java)
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
                 Intent(context, ProfileFragment::class.java)

            }
            val alert = alertDialog.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()

        }
    }

    fun observeLiveData() {
        profileFragmentViewModel.kullaniciAdi.observe(viewLifecycleOwner, Observer { kullaniciAdin ->
            kullaniciAdin?.let {
                profilUserName.visibility = View.VISIBLE
                profilUserName.text = "Hoşgeldin " + it[0].userName
            }
        })
    }
}