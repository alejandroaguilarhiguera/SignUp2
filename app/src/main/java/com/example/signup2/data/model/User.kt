package com.example.signup2.data.model

data class Avatar(val url: String)
data class User(
    val id: Number,
    val name: String,
    val lastname: String,
    val email: String,
    val RoleId: Number,
    val fullname: String,
    val phone: String,
    val avatar: Avatar,
    val enabled: Boolean,
    val confirmed: Boolean,
    val lastSignIn: String,
    val createdBy: Number,
    val updatedBy: Number,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String,
    val Role: Role,
)