package com.example.medideals.ui.activities.home.orderPlaced

import androidx.recyclerview.widget.DiffUtil


class DiffUtilOrderPlaced : DiffUtil.ItemCallback<com.example.medideals.data.model.orderPlaced.Result>() {
    override fun areItemsTheSame(oldItem: com.example.medideals.data.model.orderPlaced.Result, newItem: com.example.medideals.data.model.orderPlaced.Result): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: com.example.medideals.data.model.orderPlaced.Result, newItem: com.example.medideals.data.model.orderPlaced.Result): Boolean {
        return oldItem.date == newItem.date

    }

}