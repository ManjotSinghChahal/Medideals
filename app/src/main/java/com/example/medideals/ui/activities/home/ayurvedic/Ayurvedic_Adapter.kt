package com.example.medideals.ui.activities.home.ayurvedic

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R

class Ayurvedic_Adapter(val context: Context) : RecyclerView.Adapter<Ayurvedic_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.ayurvedic_adpt, container, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews() {

            val mrpPrice_ayurvedic: TextView = itemView.findViewById(R.id.mrpPrice_ayurvedic) as TextView

            mrpPrice_ayurvedic.paintFlags = mrpPrice_ayurvedic.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG

        }
    }

}