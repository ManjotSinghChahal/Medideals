package com.example.medideals.data.model.orderPlaced

data class Result(
    val date: String,
    val details: Details,
    val money_status: String,
    val order_id: String,
    val phone: String,
    val total_amount: String
)