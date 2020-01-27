package com.example.medideals.ui.activities.home.showResponses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.responses.Message
import com.example.medideals.data.model.responses.Responses
import kotlinx.android.synthetic.main.showresponses_adpt.view.*

class ShowResponses_Adapter(
    val context: Context,
    val responses: Responses
) : RecyclerView.Adapter<ShowResponses_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.showresponses_adpt, container, false))
    }

    override fun getItemCount(): Int {
        return responses.message.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(responses.message)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(responseList: List<Message>) {

            itemView.id_response.text = responseList.get(adapterPosition).response_id
            itemView.vendorId_response.text = responseList.get(adapterPosition).vendor_id
            itemView.vendorEmail_response.text = responseList.get(adapterPosition).vendor_email
            itemView.date_response.text = responseList.get(adapterPosition).date
            itemView.message_responses.text = responseList.get(adapterPosition).message
        }
    }

}