package com.example.signup2.data.model
data class UserData(val name: String, val lastname: String, val phone: String, val email: String)
data class SignUpPostData(val sid: String, val user: UserData)
