package com.example.signup2.ui.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import com.example.signup2.DashboardActivity
import com.example.signup2.data.LoginDataSource
import com.example.signup2.data.LoginRepository
import com.example.signup2.data.Result
import com.example.signup2.data.UserDataSource
import com.example.signup2.databinding.ActivitySignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private var phoneNumber = ""
    private var sid = ""
    private var name = ""
    private var lastName = ""
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras: Bundle = this.intent.extras!!
        phoneNumber = extras.getString("phone") ?: ""
        sid = extras.getString("sid") ?: ""



        binding.btnRegister.setOnClickListener {

            MainScope().launch {
                name = binding.etFirstName.text.toString()
                lastName = binding.etLastName.text.toString()
                email = binding.etEmail.text.toString()
                val result = withContext(Dispatchers.Default) { UserDataSource().signUp(sid, name, lastName, phoneNumber, email) }
                if (result is Result.Success) {
                    val token = result.data.token ?: ""
                    if (token !== "") {
                        val intent = Intent(this@SignUpActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@SignUpActivity, "Ocurrió un error con la respuesta del servidor", Toast.LENGTH_SHORT).show()
                    }

                    // Enviar al dashboar si responde con datos de sessión
                } else {
                    Toast.makeText(this@SignUpActivity, "Ocurrió un error", Toast.LENGTH_SHORT).show()
                }

            }

        }


    }
}