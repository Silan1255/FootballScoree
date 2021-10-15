package com.tahmin.footballscore.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahmin.footballscore.R
import com.tahmin.footballscore.adapter.ArrangementAdapter
import com.tahmin.footballscore.viewModel.ArrangementFragmentViewModel
import kotlinx.android.synthetic.main.fragment_arrangement.*
import kotlinx.android.synthetic.main.predictions_list_item.*

class ArrangementFragment : Fragment() {
    private val siralamaAdapter = ArrangementAdapter(arrayListOf())
    private lateinit var arrangementFragmentViewModel: ArrangementFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_arrangement, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrangementFragmentViewModel = ViewModelProviders.of(this).get(ArrangementFragmentViewModel::class.java)
        arrangementFragmentViewModel.refreshData()
        siralama_progressBar.visibility = View.VISIBLE
        txt_siralama.layoutManager = LinearLayoutManager(context)
            txt_siralama.adapter = siralamaAdapter

        siralamaAdapter.userClickListener = { userId->
            var intent = Intent(context, PredictionsFragment::class.java)
            intent.putExtra("Key", "afterGuess")
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        observeLiveData()
    }
    fun observeLiveData() {
        arrangementFragmentViewModel.skor.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
              txt_siralama.visibility = View.VISIBLE
              siralamaAdapter.SiralamayiGüncelle(it)
            }
            siralama_progressBar.visibility = View.GONE

        })

        arrangementFragmentViewModel.haveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.not()){
                    siralama_progressBar.visibility = View.GONE
                    tv_have_data_msg.visibility = View.VISIBLE
                }
            }
        })
    }
}