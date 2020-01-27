package com.example.medideals.data.model.totalCount

data class BuyingDetails(
    val lastOrder: String,
    val subscriptionEndDate: String,
    val totalMoneySpend: String,
    val totalOrderPlaced: Int,
    val totalOrderReceived: Int,
    val totalOrderReturn: Int,
    val totalProductReceived: Int,
    val totalProductReturn: Int
)