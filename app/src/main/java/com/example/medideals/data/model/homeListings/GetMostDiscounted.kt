package com.example.medideals.data.model.homeListings

data class GetMostDiscounted(
    val barnd_name: String,
    val code: String,
    val discount: String,
    val minquantity: String,
    val old_price: String,
    val price: String,
    val product_id: String,
    val product_status: String,
    val title: String
)