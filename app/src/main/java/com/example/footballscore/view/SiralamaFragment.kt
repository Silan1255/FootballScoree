package com.example.footballscore.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.R
import com.example.footballscore.adapter.SiralamaAdapter
import com.example.footballscore.databinding.FragmentSiralamaBinding
import com.example.footballscore.viewModel.SiralamaFragmentViewModel
import kotlinx.android.synthetic.main.fragment_siralama.*
import java.util.Observer

class SiralamaFragment : Fragment() {
    private val siralamaAdapter = SiralamaAdapter(arrayListOf())
    private lateinit var siralamaFragmnetViewModel: SiralamaFragmentViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_siralama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        siralamaFragmnetViewModel = ViewModelProviders.of(this).get(SiralamaFragmentViewModel::class.java)
        siralamaFragmnetViewModel.refreshData()
        siralama_progressBar.visibility = View.VISIBLE


        txt_siralama.layoutManager = LinearLayoutManager(context)
            txt_siralama.adapter = siralamaAdapter

        siralamaAdapter.userClickListener = { userId->
            var intent = Intent(context, TahminlerVeYorumlar::class.java)
            intent.putExtra("Key", "afterGuess")
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        observeLiveData()
    }
    fun observeLiveData() {
        siralamaFragmnetViewModel.skor.observe(viewLifecycleOwner, androidx.lifecycle.Observer {  Skor ->
            Skor?.let {
              txt_siralama.visibility = View.VISIBLE
              siralamaAdapter.SiralamayiGüncelle(Skor)
            }
            siralama_progressBar.visibility= View.GONE
        })
    }
}