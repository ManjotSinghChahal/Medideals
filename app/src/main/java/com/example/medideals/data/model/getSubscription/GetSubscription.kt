package com.example.medideals.data.model.getSubscription

data class GetSubscription(
    val message: String,
    val record: List<Record>,
    val status: String
)