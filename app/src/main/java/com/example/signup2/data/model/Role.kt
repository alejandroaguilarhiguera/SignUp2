package com.example.signup2.data.model

data class Role (
    val id: Number,
    val name: String,
    val description: String?,
    val updatedBy: Number?,
    val createdBy: Number?,
    val createdAt: String?,
    val updatedAt: String?,
    val deletedAt: String?,
    val Permissions: Array<Permission>?,
)