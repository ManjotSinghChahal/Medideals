package com.example.medideals.data.model.orderReceivedModel

data class Details(
    val billTo: String,
    val bill_address: String,
    val company: String,
    val email: String,
    val order_no: String,
    val per_price: Double,
    val phone: String,
    val product_name: String,
    val quantity: String,
    val shipTo: String,
    val shipaddress: String,
    val total_price: String
)