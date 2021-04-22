package com.example.signup2.ui.LoginPhone

import com.example.signup2.data.model.DefaultCallbackRequest
import com.example.signup2.data.model.ErrorRequest

data class LoginPhoneResult(
    val success: DefaultCallbackRequest? = null,
    val error: ErrorRequest? = null
)
