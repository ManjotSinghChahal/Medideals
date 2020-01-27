package com.example.medideals.data.model.orderReceivedModel

data class OrderReceivedModel(
    val result: List<OrderReceivedResult>,
    val status: String,
    val totalpage: String
)