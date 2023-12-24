package com.hisham.baseto.data.api

import com.hisham.baseto.data.models.cart.CartDataModel
import com.hisham.baseto.data.models.categories.CategoryDetailsModel
import com.hisham.baseto.data.models.banners.BannersModel
import com.hisham.baseto.data.models.categories.CategoriesModel
import com.hisham.baseto.data.models.home.HomeDataModel
import com.hisham.baseto.data.models.product.ProductDetailsModel
import com.hisham.baseto.data.models.user.UserDataModel
import com.hisham.baseto.utils.Constants.Companion.userToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitCalls {
    @FormUrlEncoded
    @POST("login")

    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<UserDataModel>

    @FormUrlEncoded
    @POST("register")

    suspend fun registerUser(
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("image") image: String
    ): Response<UserDataModel>

    @GET("banners")

    suspend fun getHomeBanners(): Response<BannersModel>

    @GET("categories")

    suspend fun getCategories(): Response<CategoriesModel>

    @GET("categories/{id}")

    suspend fun getCategoryDetails(@Path("id") id: String): Response<CategoryDetailsModel>

    @GET("home")

    suspend fun getHomeData(): Response<HomeDataModel>

    @GET("products/{id}")

    suspend fun getProductData(@Path("id") id: String): Response<ProductDetailsModel>

    @GET("carts")

    suspend fun getCart(@Header("Authorization") token: String? = userToken): Response<CartDataModel>

    @POST("carts")

    suspend fun addToCart(@Header("Authorization") token: String? = userToken,@Body data: Map<String, String>): Response<Map<String, Any>>
}