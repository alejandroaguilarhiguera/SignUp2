package com.example.signup2.data

import android.util.Log
import com.example.signup2.data.model.*
import retrofit2.awaitResponse
import java.io.IOException

class UserDataSource {
    suspend fun signUp(sid: String, name: String, lastName: String, phone: String, email: String): Result<LoggedInUser> {
        val client = ServiceBuilder.buildService()
        try {
            val user = UserData(name, lastName, phone, email)
            val response = client.signUp(SignUpPostData(sid, user)).awaitResponse()
            if(response.isSuccessful) {
                return Result.Success(response.body() as LoggedInUser)
            }
            return Result.Error(IOException(response.message()))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

}