package com.hisham.baseto.data.api

import com.hisham.baseto.data.models.user.UserDataModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitCalls {
    @FormUrlEncoded
    @POST("login")
   suspend fun loginUser(@Field("email") email:String,@Field("password") password:String):Response<UserDataModel>
}