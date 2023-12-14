package com.hisham.baseto.domain.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.hisham.baseto.data.api.RetrofitInstance
import com.hisham.baseto.data.database.KelineDao
import com.hisham.baseto.data.models.user.User
import com.hisham.baseto.data.models.user.UserDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val database: KelineDao, private val activity: Activity) {
    suspend fun userLogin(email: String, password: String): Response<UserDataModel> {
        //  var user: User
        var response: Response<UserDataModel>
        withContext(Dispatchers.IO) {
            response = RetrofitInstance.retrofitInstance.loginUser(email, password)
            if (response.code() == 200) {
                val user = response.body()?.data ?: User()
                database.insertUser(user)
                val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("userToken", user.token).apply()

                Log.d("UserName", user.token ?: "")
            }
        }

        return response
    }

    suspend fun userRegister(
        email: String,
        password: String,
        phone: String,
        name: String,
        image: String
    ): Response<UserDataModel> {
        //var user: User
        var response: Response<UserDataModel>
        withContext(Dispatchers.IO) {
            response =
                RetrofitInstance.retrofitInstance.registerUser(email, phone, name, password, image)
            if (response.code() == 200) {
                Log.i("registerRespone", response.body()?.message ?: "")
                val user = response.body()?.data ?: User()
                database.insertUser(user)
                val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("userToken", user.token).apply()
            }
        }
        //  Log.d("UserName", user.token ?: "")
        return response
    }

    suspend fun getUser(id: Int): User {

        val user: User
        withContext(Dispatchers.IO) {
            user = database.getUserById(id)
        }
//        Log.d("userToken", user.token ?: "")
        return user
    }
}