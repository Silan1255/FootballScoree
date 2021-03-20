package com.example.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.model.maclar.GetMaclar.GetMaclarItem
import kotlinx.android.synthetic.main.list_item.view.*

class FutbolAdapter (val futbolModelListesi: ArrayList<GetMaclarItem>) : RecyclerView.Adapter<FutbolAdapter.FutbolViewHolder> () {

    class FutbolViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutbolViewHolder {
       val inflater= LayoutInflater.from(parent.context)
      val view= inflater.inflate(R.layout.list_item, parent, false)
      return  FutbolViewHolder(view)
    }

    override fun onBindViewHolder(holder: FutbolViewHolder, position: Int) {
        holder.itemView.FirstTeam.text= futbolModelListesi.get(position).firsTeam
        holder.itemView.SecondTeam.text= futbolModelListesi.get(position).secondTeam
        holder.itemView.MacSonucu.text= futbolModelListesi.get(position).macSonucu



    }

    override fun getItemCount(): Int = futbolModelListesi.size


    fun gecmisMacSkorlariniGüncelle(yeniFutbolModelListesi: List<GetMaclarItem>){
        futbolModelListesi.clear()
        futbolModelListesi.addAll(yeniFutbolModelListesi)
        notifyDataSetChanged()

    }
}