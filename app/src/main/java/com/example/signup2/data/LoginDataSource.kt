package com.example.signup2.data

import android.util.Log
import retrofit2.awaitResponse
import java.io.IOException
import com.example.signup2.data.Result
import com.example.signup2.data.model.*

class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        val client = ServiceBuilder.buildService()
        try {
            val response = client.authLogin(LoginPostData(username, password)).awaitResponse()
            if(response.isSuccessful) {
                return Result.Success(response.body() as LoggedInUser)
            }
            return Result.Error(IOException(response.message()))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }

    }

    suspend fun confirmPhone(phone: String, code: String): Result<LoggedInUser> {
        val client = ServiceBuilder.buildService()
        try {
            val response = client.confirmPhone(ConfirmPhonePostData(phone, code)).awaitResponse()
            if(response.isSuccessful) {
                return Result.Success(response.body() as LoggedInUser)
            }
            return Result.Error(IOException(response.message()))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    suspend fun sendCodePhone(phone: String): Result<DefaultCallbackRequest> {
        val client = ServiceBuilder.buildService()
        try {
            val response = client.sendCodePhone(SendPhonePostData(phone)).awaitResponse()
            if(response.isSuccessful) {
                return Result.Success(response.body() as DefaultCallbackRequest)
            }
            return Result.Error(IOException(response.message()))
        } catch (e: Throwable) {
            Log.i("test", e.message.toString())
            return Result.Error(IOException("Error logging in", e))
        }
    }

    suspend fun refreshToken(token: String): Result<LoggedInUser> {
        val client = ServiceBuilder.buildService()
        try {
            val response = client.refreshToken(token).awaitResponse()
            if(response.isSuccessful) {
                return Result.Success(response.body() as LoggedInUser)
            }
            return Result.Error(IOException(response.message()))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

}