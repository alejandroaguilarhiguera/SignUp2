package com.example.signup2.ui.LoginPhone

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.signup2.data.LoginDataSource
import com.example.signup2.data.LoginRepository

class LoginPhoneViewModelFactory: ViewModelProvider.Factory {
    private var context: Context? = null
    constructor(context: Context) {
        this.context = context
    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginPhoneViewModel::class.java)) {
            return LoginPhoneViewModel(
                loginRepository = LoginRepository(
                    context = this.context!!,
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}