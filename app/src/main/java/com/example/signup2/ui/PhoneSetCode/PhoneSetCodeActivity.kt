package com.example.signup2.ui.PhoneSetCode

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.signup2.DashboardActivity
import com.example.signup2.ui.SignUp.SignUpActivity
import com.example.signup2.data.LoginDataSource
import com.example.signup2.data.LoginRepository
import com.example.signup2.data.Result
import com.example.signup2.databinding.ActivityPhoneSetCodeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class phoneSetCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneSetCodeBinding
    private var phoneNumber = ""
    private var phoneCode = ""
    private val requestReadSms = 2
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneSetCodeBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val extras: Bundle = this.intent.extras!!
        phoneNumber = extras.getString("phone") ?: ""
        binding.btnSetCodePhone.setOnClickListener{
            phoneCode = binding.etCodePhone.text.toString()
            MainScope().launch {
                val result = withContext(Dispatchers.Default) { LoginRepository(this@phoneSetCodeActivity, LoginDataSource()).confirmCodePhone(phoneNumber, phoneCode) }
                if (result is Result.Success) {
                    val sid = result.data.sid ?: ""
                    if (sid !== "") {
                        val intent = Intent(this@phoneSetCodeActivity, SignUpActivity::class.java)
                        intent.putExtra("sid", sid)
                        intent.putExtra("phone", phoneNumber)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@phoneSetCodeActivity, DashboardActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                    // Enviar al dashboar si responde con datos de sessión
                } else {
                    Toast.makeText(this@phoneSetCodeActivity, "Ocurrió un error", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }


}