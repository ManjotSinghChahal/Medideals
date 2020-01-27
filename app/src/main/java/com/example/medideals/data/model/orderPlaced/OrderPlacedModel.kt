package com.example.medideals.data.model.orderPlaced

data class OrderPlacedModel(
    val result: List<Result>,
    val status: String,
    val totalpage: String
)