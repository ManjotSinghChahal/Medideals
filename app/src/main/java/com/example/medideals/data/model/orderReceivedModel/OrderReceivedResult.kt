package com.example.medideals.data.model.orderReceivedModel

data class OrderReceivedResult(
    val customer_number: String,
    val date: String,
    val details: Details,
    val docket_number: String,
    val list_id: String,
    val money_status: String,
    val order_id: String,
    val order_status: String
)