package com.tahmin.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahmin.footballscore.R
import com.tahmin.footballscore.model.maclar.GetMaclar.GetMaclarItem
import kotlinx.android.synthetic.main.list_item.view.*

class FootballAdapter(val futbolModelListesi: ArrayList<GetMaclarItem>) : RecyclerView.Adapter<FootballAdapter.FutbolViewHolder>() {

    var macItemClıckLıstener: (String, String) -> Unit = { _, _ -> }

    class FutbolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutbolViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return FutbolViewHolder(view)
    }

    override fun onBindViewHolder(holder: FutbolViewHolder, position: Int) {
        holder.itemView.FirstTeam.text = futbolModelListesi.get(position).firsTeam
        holder.itemView.SecondTeam.text = futbolModelListesi.get(position).secondTeam
        holder.itemView.MacSonucuTeam.text = futbolModelListesi.get(position).macSonucu
        holder.itemView.setOnClickListener {
            macItemClıckLıstener(futbolModelListesi[position].firsTeam, futbolModelListesi[position].skorID)
        }
    }

    override fun getItemCount(): Int = futbolModelListesi.size
    fun gecmisMacSkorlariniGüncelle(yeniFutbolModelListesi: List<GetMaclarItem>) {
        futbolModelListesi.clear()
        futbolModelListesi.addAll(yeniFutbolModelListesi)
        notifyDataSetChanged()
    }
}