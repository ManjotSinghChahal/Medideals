package com.example.medideals.ui.activities.home.orderReceived

import androidx.recyclerview.widget.DiffUtil
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult


class DiffUtilCallBackOrderRec : DiffUtil.ItemCallback<OrderReceivedResult>() {
    override fun areItemsTheSame(oldItem: OrderReceivedResult, newItem: OrderReceivedResult): Boolean {
        return oldItem.customer_number == newItem.customer_number
    }

    override fun areContentsTheSame(oldItem: OrderReceivedResult, newItem: OrderReceivedResult): Boolean {
        return oldItem.customer_number == newItem.customer_number

    }

}