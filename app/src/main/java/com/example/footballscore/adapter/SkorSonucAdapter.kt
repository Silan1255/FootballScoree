package com.example.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.model.skor_tahmin.GetTahminMaclar
import com.example.footballscore.model.skor_tahmin.SkorSonucItem
import kotlinx.android.synthetic.main.profil_list_item.view.*

class SkorSonucAdapter(val sonucListesi: ArrayList<SkorSonucItem>) : RecyclerView.Adapter<SkorSonucAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    var isAfterGuess: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.profil_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkorSonucAdapter.ViewHolder, position: Int) {
        if (isAfterGuess){
            if (sonucListesi.get(position).guess == sonucListesi.get(position).macSonucu){
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.teal_200))
            } else{
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.orangey_red))
            }
        }
        holder.itemView.txt_mac_skorum.text= sonucListesi.get(position).guess
        holder.itemView.txt_birinci_takim.text= sonucListesi.get(position).firsTeam
        holder.itemView.txt_ikinci_takim.text= sonucListesi.get(position).secondTeam

    }

    override fun getItemCount(): Int = sonucListesi.size

    fun gecmisSkorlariGuncelle(yeniTahminSonucListesi: List<SkorSonucItem>, isAfterGuessDetect: Boolean){
        sonucListesi.clear()
        sonucListesi.addAll(yeniTahminSonucListesi)
        isAfterGuess = isAfterGuessDetect
        notifyDataSetChanged()
    }

}
