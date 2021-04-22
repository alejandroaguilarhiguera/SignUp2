package com.example.signup2.data.model

data class ErrorRequest(
    val statusCode: Number,
    val error: String,
    val message: String,
    val data: String?,
)
