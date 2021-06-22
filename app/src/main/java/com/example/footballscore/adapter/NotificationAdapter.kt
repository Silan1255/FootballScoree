package com.example.footballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.model.notification.GetNotificationItem
import com.example.footballscore.model.skor.GetUserScoreItem
import kotlinx.android.synthetic.main.notifi_list_item.view.*

class NotificationAdapter(val notifiList: ArrayList<GetNotificationItem>) : RecyclerView.Adapter<NotificationAdapter.NotifiViewHolder>() {
    class NotifiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifiViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.notifi_list_item, parent, false)
        return NotifiViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: NotifiViewHolder, position: Int) {
        holder.itemView.notifi_header.text = notifiList[position].notifiHeader
        holder.itemView.notifi_text.text= notifiList[position].notifiText
    }

    override fun getItemCount(): Int = notifiList.size

    fun BildiriyiGüncelle(yeniBildiriListesi: List<GetNotificationItem>) {
        notifiList.clear()
        notifiList.addAll(yeniBildiriListesi)
        notifyDataSetChanged()
    }
}