package com.example.signup2.ui.LoginPhone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signup2.data.LoginRepository
import com.example.signup2.data.model.DefaultCallbackRequest
import com.example.signup2.data.model.ErrorRequest
import com.example.signup2.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPhoneViewModel(private val loginRepository: LoginRepository): ViewModel() {

    private val _phoneForm = MutableLiveData<PhoneFormState>()
    val phoneFormState: LiveData<PhoneFormState> = _phoneForm

    private val _loginPhoneResult = MutableLiveData<LoginPhoneResult>()
    val loginPhoneResult: LiveData<LoginPhoneResult> = _loginPhoneResult

    fun sendCodePhone(phone: String) {
        Log.i("TEST", "Prueba1")
        MainScope().launch {
            Log.i("TEST", "Prueba2")

            val result = withContext(Dispatchers.IO) { loginRepository.sendCodePhone(phone) }
            if (result is Result.Success) {
                _loginPhoneResult.value = LoginPhoneResult(success = DefaultCallbackRequest(result.data.message))
            } else {
                _loginPhoneResult.value = LoginPhoneResult(error= ErrorRequest(401,"Error de validación","Error login",null))
            }
        }
        Log.i("TEST", "Prueba3")

    }

}