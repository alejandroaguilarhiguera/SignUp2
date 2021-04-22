package com.example.signup2.data

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.signup2.data.model.DefaultCallbackRequest
import com.example.signup2.data.model.LoggedInUser
import com.example.signup2.data.model.User
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import org.json.JSONObject
import org.xml.sax.Parser
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class LoginRepository(val context: Context, val dataSource: LoginDataSource?) {
    private var prefs: Prefs? = null
    private val gson = Gson()
    // in-memory cache of the loggedInUser object
    var session: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = session != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        prefs = Prefs(context)

        val date = LocalDate.now()
        val accessTokenExpiresIn = prefs!!.accessTokenExpiresIn
        val refreshTokenExpiresIn = prefs!!.refreshTokenExpiresIn
        //val date = Date(refreshTokenExpiresIn)

        if (refreshTokenExpiresIn !== "") {

            //val refreshDate = LocalDate.parse(refreshTokenExpiresIn, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            //val unix = refreshDate.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
                //val toRefresh = LocalDate.parse("2021-04-24", DateTimeFormatter.ISO_LOCAL_DATE)
            //Log.i("TEST frefresh",LocalDate.parse("2021-04-24T22:23:41.289Z", DateTimeFormatter.ISO_INSTANT))
            //Log.i("TEST frefresh",refreshTokenExpiresIn)

        }

        // refreshTokenExpiresIn < cal.time
        if (prefs!!.token === "" && prefs!!.refreshToken === "") {
            session = null
        } else {
            session = LoggedInUser(
                prefs!!.token,
                prefs!!.refreshToken,
                accessTokenExpiresIn,
                refreshTokenExpiresIn,
                gson.fromJson(prefs!!.user, User::class.java),
                null,
            )
        }
    }

    fun logout() {
        session = null
        prefs = Prefs(context)
        prefs!!.token = ""
        //prefs!!.refreshToken = ""
        //prefs!!.accessTokenExpiresIn = ""
        //prefs!!.refreshTokenExpiresIn = ""
        prefs!!.user = ""
    }

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource!!.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    suspend fun refreshToken(): Result<LoggedInUser> {
        var token = ""
        if (this.session !== null) {
            token = this.session!!.refreshToken ?: ""
        }
        val result = dataSource!!.refreshToken(token)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    suspend fun sendCodePhone(phone: String): Result<DefaultCallbackRequest> {
        return dataSource!!.sendCodePhone(phone)
    }

    suspend fun confirmCodePhone(phone: String, code: String): Result<LoggedInUser> {
        val result = dataSource!!.confirmPhone(phone, code)
        if (result is Result.Success) {
            val token = result.data.token ?: ""
            if (token !== "") {
                setLoggedInUser(result.data)
            }
        }
        return result
    }
    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.session = loggedInUser
        prefs = Prefs(context)
        prefs!!.token = loggedInUser.token
        prefs!!.refreshToken = loggedInUser.refreshToken
        prefs!!.accessTokenExpiresIn = loggedInUser.accessTokenExpiresIn
        prefs!!.refreshTokenExpiresIn = loggedInUser.refreshTokenExpiresIn
        prefs!!.user = gson.toJson(loggedInUser.user).toString()
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}