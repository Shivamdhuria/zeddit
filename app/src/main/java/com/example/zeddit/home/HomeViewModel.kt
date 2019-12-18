package com.cloud.io.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.zeddit.home.LoginRepository
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(private val repository: LoginRepository) :
    ViewModel() {

    private val loginInput = MutableLiveData<String>()

    internal val login by lazy { loginInput.switchMap(repository::login) }

    fun login(phoneNumber: String) {
        loginInput.value = phoneNumber
    }
}
