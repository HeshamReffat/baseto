package com.hisham.baseto.domain.viewmodels.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.domain.Constants
import com.hisham.baseto.domain.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(val repo: UserRepository, val context: Context) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val _loading = MutableLiveData<Constants.ApiStatus?>()
    val loading: LiveData<Constants.ApiStatus?>
        get() = _loading


    fun loginUser() {
        val user = username.value ?: ""
        val pass = password.value ?: ""
        viewModelScope.launch {
            _loading.value = Constants.ApiStatus.LOADING
            try {
                if (user.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val userData = repo.userLogin(user, pass)
                    getUser(userData.id ?: 0)
                    _loading.value = Constants.ApiStatus.DONE
                }
            } catch (e: Exception) {
                Log.i("ApiError", e.message.toString())
                _loading.value = Constants.ApiStatus.ERROR
            }
        }

    }

    fun getUser(id: Int) {
        viewModelScope.launch {
            repo.getUser(id)
            Toast.makeText(context, "User Data Retrieved", Toast.LENGTH_SHORT)
                .show()
        }
    }
}