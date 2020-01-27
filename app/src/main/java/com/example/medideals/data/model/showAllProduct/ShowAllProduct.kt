package com.example.medideals.data.model.showAllProduct

data class ShowAllProduct(
    val result: List<ShowAllProductResult>,
    val status: String,
    val totalpage: String
)