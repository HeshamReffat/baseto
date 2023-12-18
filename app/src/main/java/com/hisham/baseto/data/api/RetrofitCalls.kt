package com.hisham.baseto.data.api

import com.hisham.baseto.data.models.banners.BannersModel
import com.hisham.baseto.data.models.categories.CategoriesModel
import com.hisham.baseto.data.models.home.HomeDataModel
import com.hisham.baseto.data.models.user.UserDataModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitCalls {
    @FormUrlEncoded
    @POST("login")
    @Headers("lang:en")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<UserDataModel>

    @FormUrlEncoded
    @POST("register")
    @Headers("lang:en")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("image") image: String
    ): Response<UserDataModel>
    @GET("banners")
    @Headers("lang:en")
    suspend fun getHomeBanners():Response<BannersModel>
    @GET("categories")
    @Headers("lang:en")
    suspend fun getCategories():Response<CategoriesModel>

    @GET("home")
    @Headers("lang:en")
    suspend fun getHomeData():Response<HomeDataModel>
}