package com.example.medideals.data.model.otp

data class Otp(
    val message: String,
    val otp: Int,
    val status: String,
    val user_id: String
)