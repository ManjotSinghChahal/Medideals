package com.example.medideals.data.model.getCart

data class Record(
    val `data`: List<Data>,
    val login_status: Int,
    val subtotal: String,
    val total: String
)