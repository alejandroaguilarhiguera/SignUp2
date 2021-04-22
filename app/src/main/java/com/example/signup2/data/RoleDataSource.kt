package com.example.signup2.data

import android.content.Context
import com.example.signup2.data.model.Role
import retrofit2.awaitResponse
import java.io.IOException
import com.example.signup2.data.Result

class RoleDataSource {
    suspend fun getAll(context: Context): Result<List<Role>> {
        val client = ServiceBuilder.buildService(context)
       try {
            val response = client.getRoles().awaitResponse()
            if(response.isSuccessful) {
                return Result.Success(response.body() as List<Role>)
            }
            return Result.Error(IOException(response.message()))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error get Roles", e))
        }
    }
}