package com.example.footballscore.adapter

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballscore.R
import com.example.footballscore.model.skor_tahmin.GetTahminMaclar
import kotlinx.android.synthetic.main.fragment_football.view.*
import kotlinx.android.synthetic.main.tahmin_list_item.view.*

class TahminAdapter(val futbolTahminModelListesi: ArrayList<GetTahminMaclar.GetTahminMaclarItem> ): RecyclerView.Adapter<TahminAdapter.TahminViewHolder>() {
    var macTahminItemClıckLıstener: (String, String, String,String) -> Unit = { _,_,_,_ ->}
    class TahminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TahminViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.tahmin_list_item, parent, false)
        return TahminViewHolder(view)
    }

    override fun onBindViewHolder(holder: TahminViewHolder, position: Int) {
        holder.itemView.TahminFirstTeam.text= futbolTahminModelListesi.get(position).tahminFirsTeam
        holder.itemView.TahminSecoundTeam.text= futbolTahminModelListesi.get(position).tahminSecondTeam
        holder.itemView.canliSkor.text= futbolTahminModelListesi.get(position).tahminMacSonucu
        holder.itemView.canliDakika.text= futbolTahminModelListesi.get(position).tahminDakika
        Glide.with(holder.itemView.context).load(futbolTahminModelListesi.get(position).tahminImage).into(holder.itemView.ligName)
        if (holder.itemView.tahmin_swip.isOpened){
            holder.itemView.tahmin_swip.close(true)
        }
        holder.itemView.guessButton.setOnClickListener {
            macTahminItemClıckLıstener(futbolTahminModelListesi[position].tahminFirsTeam,
                futbolTahminModelListesi[position].tahminSecondTeam,
                futbolTahminModelListesi[position].tahminSkorID,
                futbolTahminModelListesi[position].tahminDakika
            )

        }
    }

    override fun getItemCount(): Int =futbolTahminModelListesi.size

    fun gecmisTahminleriGuncelle(yeniTahminListesi: List<GetTahminMaclar.GetTahminMaclarItem>){
        futbolTahminModelListesi.clear()
        futbolTahminModelListesi.addAll(yeniTahminListesi)
        notifyDataSetChanged()
    }

}
