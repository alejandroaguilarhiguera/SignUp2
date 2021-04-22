package com.example.signup2.data

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    private val PREFS_NAME = "com.Login5"
    private val TOKEN = "token"
    private val REFRESH_TOKEN = "refreshToken"
    private val ACCESS_TOKEN_EXPIRES_IN = "accessTokenExpiresIn"
    private val REFRESH_TOKEN_EXPIRES_IN = "refreshTokenExpiresIn"
    private val USER = "user"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var token: String
        get() = prefs.getString(TOKEN, "") ?: ""
        set(value) = prefs.edit().putString(TOKEN, value).apply()

    var refreshToken: String
        get() = prefs.getString(REFRESH_TOKEN, "") ?: ""
        set(value) = prefs.edit().putString(REFRESH_TOKEN, value).apply()

    var accessTokenExpiresIn: String
        get() = prefs.getString(ACCESS_TOKEN_EXPIRES_IN, "") ?: ""
        set(value) = prefs.edit().putString(ACCESS_TOKEN_EXPIRES_IN, value).apply()

    var refreshTokenExpiresIn: String
        get() = prefs.getString(REFRESH_TOKEN_EXPIRES_IN, "") ?: ""
        set(value) = prefs.edit().putString(REFRESH_TOKEN_EXPIRES_IN, value).apply()
    var user : String
        get() = prefs.getString(USER, "") ?: ""
        set(value) = prefs.edit().putString(USER, value).apply()

}