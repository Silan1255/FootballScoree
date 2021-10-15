package com.tahmin.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahmin.footballscore.R
import com.tahmin.footballscore.model.skor_tahmin.GetTahminMaclar
import kotlinx.android.synthetic.main.predictions_list_item.view.*

class PredictionAdapter(val futbolTahminModelListesi: ArrayList<GetTahminMaclar.GetTahminMaclarItem> ): RecyclerView.Adapter<PredictionAdapter.TahminViewHolder>() {
    var macTahminItemClıckLıstener: (String, String, String,String) -> Unit = { _,_,_,_ ->}
    class TahminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TahminViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.predictions_list_item, parent, false)
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
                futbolTahminModelListesi[position].tahminMacID,
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
