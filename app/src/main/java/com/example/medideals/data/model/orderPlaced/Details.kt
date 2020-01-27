package com.example.medideals.data.model.orderPlaced

data class Details(
    val billTo: String,
    val bill_address: String,
    val email: String,
    val order_no: String,
    val phone: String,
    val productDetails: List<ProductDetail>,
    val shipTo: String,
    val shipaddress: String,
    val total_price: String
)