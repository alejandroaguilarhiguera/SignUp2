package com.example.signup2.ui.LoginPhone

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.signup2.DashboardActivity
import com.example.signup2.R
import com.example.signup2.data.Result
import com.example.signup2.data.LoginDataSource
import com.example.signup2.data.LoginRepository
import com.example.signup2.databinding.ActivityLoginPhoneBinding
import com.example.signup2.ui.PhoneSetCode.phoneSetCodeActivity
import com.example.signup2.ui.SignUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_login_phone.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor

class loginPhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPhoneBinding
    private lateinit var loginPhoneViewModel: LoginPhoneViewModel
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPhoneBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loginPhoneViewModel = ViewModelProviders.of(this, LoginPhoneViewModelFactory(this))
            .get(LoginPhoneViewModel::class.java)

        loginPhoneViewModel.phoneFormState.observe(this@loginPhoneActivity, Observer {
            val loginPhoneState = it ?: return@Observer
            val phone = binding.etPhoneNumber.text.toString()
            binding.etPhoneNumber.setText("")
            Toast.makeText(this, "State: " + phone, Toast.LENGTH_SHORT).show()
        })

        loginPhoneViewModel.loginPhoneResult.observe(this@loginPhoneActivity, Observer {
            val loginPhoneResult = it ?: return@Observer


            val phone = binding.etPhoneNumber.text.toString()
            binding.etPhoneNumber.setText("")
            Toast.makeText(this, phone, Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, phoneSetCodeActivity::class.java)
            //intent.putExtra("phone", phone)
            //startActivity(intent)
            setResult(Activity.RESULT_OK)

        })

        setSupportActionBar(findViewById(R.id.toolbar))

        binding.btnNext.setOnClickListener{
            val phone = binding.etPhoneNumber.text.toString()
            MainScope().launch {
                val result = withContext(Dispatchers.Default) { LoginDataSource().sendCodePhone(phone) }
                if (result is Result.Success) {
                    val intent = Intent(this@loginPhoneActivity, phoneSetCodeActivity::class.java)
                    intent.putExtra("phone", phone)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@loginPhoneActivity, "Ocurrio un problema", Toast.LENGTH_LONG).show()
                }
            }
        }


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@loginPhoneActivity, errString, Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                MainScope().launch {
                    val result = withContext(Dispatchers.Default) { LoginRepository(this@loginPhoneActivity, LoginDataSource()).refreshToken() }
                    if (result is Result.Success) {
                        val intent = Intent(this@loginPhoneActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@loginPhoneActivity, "Ocurrió un error", Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@loginPhoneActivity, "AUTH Failed", Toast.LENGTH_SHORT).show()
            }

        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using fingerprint authentication")
            .setNegativeButtonText("Use app password insted")
            .build()

        binding.btnBiometric.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

    }
}