package com.example.signup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signup2.data.LoginRepository
import com.example.signup2.ui.LoginPhone.loginPhoneActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Siempre entra al login, se hace inicio con huella
        // if (LoginRepository(this, null).isLoggedIn) {
        //     val intent = Intent(this, DashboardActivity::class.java)
        //     startActivity(intent)
        // } else {
        //     val intent = Intent(this, loginPhoneActivity::class.java)
        //     startActivity(intent)
        // }
        val intent = Intent(this, loginPhoneActivity::class.java)
        startActivity(intent)
        finish()
    }
}