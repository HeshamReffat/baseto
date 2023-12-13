package com.hisham.baseto.domain.viewmodels.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.domain.repository.UserRepository

class AuthViewModelFactory(val repo: UserRepository, val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repo,context) as T
        }
        throw IllegalArgumentException("Wrong ViewModel")
    }
}