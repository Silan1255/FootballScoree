package com.example.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.model.maclar.GetMaclar
import com.example.footballscore.model.skor.GetUserScoreItem
import kotlinx.android.synthetic.main.siralama_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class SiralamaAdapter(val siralamaListesi: ArrayList<GetUserScoreItem>) : RecyclerView.Adapter<SiralamaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiralamaAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.siralama_list_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: SiralamaAdapter.ViewHolder, position: Int) {
        holder.itemView.list_siralama.text = siralamaListesi.get(position).userName
        holder.itemView.list_skor.text= siralamaListesi.get(position).point


    }

    override fun getItemCount(): Int = siralamaListesi.size

    fun SiralamayiGüncelle(yeniSiralamaListesi: List<GetUserScoreItem>){
        siralamaListesi.clear()
        siralamaListesi.addAll(yeniSiralamaListesi)
        notifyDataSetChanged()
    }
}