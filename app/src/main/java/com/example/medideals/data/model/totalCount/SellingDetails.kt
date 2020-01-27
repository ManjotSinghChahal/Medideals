package com.example.medideals.data.model.totalCount

data class SellingDetails(
    val subscriptionEndDate: String,
    val totalActiveProducts: Int,
    val totalInactiveProducts: Int,
    val totalListedProducts: Int,
    val totalMoneyInEscrow: String,
    val totalOrderDelivered: Int,
    val totalOrderDispatched: Int,
    val totalOrderReceived: Int,
    val totalOrderReturn: Int,
    val totalRevenue: String
)