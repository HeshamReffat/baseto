package com.hisham.baseto.domain.viewmodels.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.utils.Constants
import com.hisham.baseto.domain.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: UserRepository, private val context: Context) : ViewModel() {
    val email = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    var image: String = ""

    private val _loading = MutableLiveData<Constants.ApiStatus?>()
    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean>
        get() = _loggedIn
    val loading: LiveData<Constants.ApiStatus?>
        get() = _loading


    fun loginUser() {
        val user = email.value ?: ""
        val pass = password.value ?: ""
        viewModelScope.launch {

            try {
                if (user.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    _loading.value = Constants.ApiStatus.LOADING
                    val response = repo.userLogin(user, pass)
                    if (response.body()?.status == true) {
                        _loggedIn.value = true
                        getUser(response.body()?.data?.id ?: 0)
                    }
                    Toast.makeText(context, response.body()?.message, Toast.LENGTH_SHORT).show()
                    _loading.value = Constants.ApiStatus.DONE
                }
            } catch (e: Exception) {
                Log.i("ApiError", e.message.toString())
                _loading.value = Constants.ApiStatus.ERROR
                _loggedIn.value = false
            }
        }

    }

    fun registerUser() {
        val email = email.value ?: ""
        val pass = password.value ?: ""
        val phone = phone.value ?: ""
        val name = name.value ?: ""
        viewModelScope.launch {

            try {
                if (email.isEmpty() && pass.isEmpty() && phone.isEmpty() && name.isEmpty() && image.isEmpty()) {
                    Toast.makeText(context, "Please enter the data", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    _loading.value = Constants.ApiStatus.LOADING
                    val response = repo.userRegister(email, pass, phone, name, image)
                    Toast.makeText(context, response.body()?.message, Toast.LENGTH_SHORT).show()
                    if (response.body()?.status == true) {
                        _loggedIn.value = true
                        val userData = response.body()?.data
                        getUser(userData?.id ?: 0)
                        clearData()
                    }

                    _loading.value = Constants.ApiStatus.DONE
                }
            } catch (e: Exception) {
                Log.i("ApiError", e.message.toString())
                _loading.value = Constants.ApiStatus.ERROR
                _loggedIn.value = false
            }
        }

    }

    private fun getUser(id: Int) {
        viewModelScope.launch {
            repo.getUser(id)
            Toast.makeText(context, "User Data Retrieved", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun clearData() {
        email.value = ""
        name.value = ""
        password.value = ""
        phone.value = ""
        image = ""
    }
}