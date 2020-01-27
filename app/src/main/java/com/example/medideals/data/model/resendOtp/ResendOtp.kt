package com.example.medideals.data.model.resendOtp

data class ResendOtp(
    val message: String,
    val otp: Int,
    val status: String
)