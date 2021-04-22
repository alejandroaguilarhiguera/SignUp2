package com.example.signup2.data.model

data class LoggedInUser(
    val token: String,
    val refreshToken: String,
    val accessTokenExpiresIn: String,
    val refreshTokenExpiresIn: String,
    val user: User?,
    val sid: String?,
)