package com.hisham.baseto.domain.repository

import android.util.Log
import com.hisham.baseto.data.api.RetrofitInstance
import com.hisham.baseto.data.database.KelineDao
import com.hisham.baseto.data.models.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val database: KelineDao) {
    suspend fun userLogin(email: String, password: String): User {
        var user: User
        withContext(Dispatchers.IO) {
            val response = RetrofitInstance.retrofitInstance.loginUser(email, password)
            user = response.body()?.data ?: User()
            database.insertUser(user)
        }
        Log.d("UserName", user.token ?: "")
        return user
    }

   suspend fun getUser(id: Int): User {

        val user: User
        withContext(Dispatchers.IO) {
            user = database.getUserById(id)
        }
        Log.d("userToken", user.token ?: "")
        return user
    }
}