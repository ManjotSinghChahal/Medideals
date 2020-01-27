package com.example.medideals.data.model.responses

data class Message(
    val date: String,
    val message: String,
    val response_id: String,
    val vendor_email: String,
    val vendor_id: String
)