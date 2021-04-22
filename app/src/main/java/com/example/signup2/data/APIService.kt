package com.example.signup2.data

import com.example.signup2.data.Constants.AUTH_CONFIRM_PHONE
import com.example.signup2.data.Constants.AUTH_LOGIN
import com.example.signup2.data.Constants.AUTH_REFRESH_TOKEN
import com.example.signup2.data.Constants.AUTH_SEND_CODE_PHONE
import com.example.signup2.data.Constants.AUTH_SIGN_UP
import com.example.signup2.data.Constants.GET_ALL_ROLES
import com.example.signup2.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @Headers("Content-Type: application/json")
    @POST(AUTH_LOGIN)
    fun authLogin(@Body loginPostData: LoginPostData): Call<LoggedInUser?>

    @Headers("Content-Type: application/json")
    @GET(GET_ALL_ROLES)
    fun getRoles(): Call<List<Role>>

    @Headers("Content-Type: application/json")
    @POST(AUTH_SEND_CODE_PHONE)
    fun sendCodePhone(@Body sendPhonePostData: SendPhonePostData): Call<DefaultCallbackRequest>

    @Headers("Content-Type: application/json")
    @POST(AUTH_CONFIRM_PHONE)
    fun confirmPhone(@Body confirmPhonePostData: ConfirmPhonePostData): Call<LoggedInUser>

    @Headers("Content-Type: application/json")
    @POST(AUTH_SIGN_UP)
    fun signUp(@Body signUpPostData: SignUpPostData): Call<LoggedInUser>

    @Headers("Content-Type: application/json")
    @GET(AUTH_REFRESH_TOKEN)
    fun refreshToken(@Path("token") token: String): Call<LoggedInUser>

}