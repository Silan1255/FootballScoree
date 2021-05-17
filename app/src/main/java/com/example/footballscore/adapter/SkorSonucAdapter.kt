package com.example.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.model.skor_tahmin.GetTahminMaclar
import com.example.footballscore.model.skor_tahmin.SkorSonucItem
import kotlinx.android.synthetic.main.profil_list_item.view.*

class SkorSonucAdapter(val sonucListesi: ArrayList<SkorSonucItem>) : RecyclerView.Adapter<SkorSonucAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.profil_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkorSonucAdapter.ViewHolder, position: Int) {
        holder.itemView.txt_mac_skorum.text= sonucListesi.get(position).guess
        holder.itemView.txt_birinci_takim.text= sonucListesi.get(position).firsTeam
        holder.itemView.txt_ikinci_takim.text= sonucListesi.get(position).secondTeam

    }

    override fun getItemCount(): Int = sonucListesi.size

    fun gecmisSkorlariGuncelle(yeniTahminSonucListesi: List<SkorSonucItem>){
        sonucListesi.clear()
        sonucListesi.addAll(yeniTahminSonucListesi)
        notifyDataSetChanged()
    }

}
