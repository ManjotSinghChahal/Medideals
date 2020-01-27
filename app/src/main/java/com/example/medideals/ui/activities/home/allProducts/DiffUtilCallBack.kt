package com.example.medideals.ui.activities.home.allProducts

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult

class DiffUtilCallBack : DiffUtil.ItemCallback<ShowAllProductResult>() {
    override fun areItemsTheSame(oldItem: ShowAllProductResult, newItem: ShowAllProductResult): Boolean {
        return oldItem.product_id == newItem.product_id
    }

    override fun areContentsTheSame(oldItem: ShowAllProductResult, newItem: ShowAllProductResult): Boolean {
        return oldItem.product_id == newItem.product_id

    }

}