package com.example.medideals.ui.activities.home.allopathic

import androidx.recyclerview.widget.DiffUtil
import com.example.medideals.data.model.allopathicModel.Result
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult

class DiffUtilcallbackAllopathic : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.product_id == newItem.product_id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.product_id == newItem.product_id

    }

}