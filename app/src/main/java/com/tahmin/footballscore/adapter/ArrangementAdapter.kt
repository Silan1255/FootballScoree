package com.tahmin.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahmin.footballscore.R
import com.tahmin.footballscore.model.skor.GetUserScoreItem
import kotlinx.android.synthetic.main.arrangement_list_item.view.*
import java.util.*

class ArrangementAdapter(val siralamaListesi: ArrayList<GetUserScoreItem>) : RecyclerView.Adapter<ArrangementAdapter.ViewHolder>() {

    var userClickListener: (String) -> Unit = { _ -> }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.arrangement_list_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.list_siralama.text = siralamaListesi.get(position).userName
        holder.itemView.list_skor.text = siralamaListesi.get(position).point
        holder.itemView.setOnClickListener {
            userClickListener(siralamaListesi.get(position).userID)
        }

        if (position == 0 || position == 1 || position == 2 ) {
            holder.itemView.img_win.setBackgroundResource(R.drawable.winner)
        }
    }
    override fun getItemCount(): Int = siralamaListesi.size

    fun SiralamayiGüncelle(yeniSiralamaListesi: List<GetUserScoreItem>) {
        siralamaListesi.clear()
        siralamaListesi.addAll(yeniSiralamaListesi)
        notifyDataSetChanged()
    }
}
