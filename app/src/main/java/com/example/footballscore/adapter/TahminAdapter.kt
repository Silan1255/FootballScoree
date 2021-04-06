package com.example.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.model.skor_tahmin.GetTahminMaclar
import kotlinx.android.synthetic.main.tahmin_list_item.view.*

class TahminAdapter(val futbolTahminModelListesi: ArrayList<GetTahminMaclar.GetTahminMaclarItem> ): RecyclerView.Adapter<TahminAdapter.TahminViewHolder>() {
    var macTahminItemClıckLıstener: (String, String) -> Unit = { _,_ ->}
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
        holder.itemView.canliDakika.text= futbolTahminModelListesi.get(position).tahminMacSaati


        holder.itemView.setOnClickListener {
            macTahminItemClıckLıstener(futbolTahminModelListesi[position].tahminFirsTeam, futbolTahminModelListesi[position].tahminSkorID)
        }
    }

    override fun getItemCount(): Int =futbolTahminModelListesi.size

    fun gecmisTahminleriGuncelle(yeniTahminListesi: List<GetTahminMaclar.GetTahminMaclarItem>){
        futbolTahminModelListesi.clear()
        futbolTahminModelListesi.addAll(yeniTahminListesi)
        notifyDataSetChanged()
    }

}
