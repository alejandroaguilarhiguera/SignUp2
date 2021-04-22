package com.example.signup2.data

import android.content.Context
import com.example.signup2.data.Constants.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private var context: Context? = null
    private var prefs: Prefs? = null
    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val token = prefs?.token ?: ""
        // TODO: Validar que el token se encuentre vigente
        val request = chain.request().newBuilder().addHeader("Authorization", "bearer $token").build()
        chain.proceed(request)
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(APIService::class.java)


    fun buildService(context: Context? = null): APIService {
        this.context = context
        if (context !== null) {
            prefs = Prefs(context)
        }
        return retrofit;
    }
}