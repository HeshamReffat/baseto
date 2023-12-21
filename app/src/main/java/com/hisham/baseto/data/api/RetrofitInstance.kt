package com.hisham.baseto.data.api

import com.hisham.baseto.BuildConfig
import com.hisham.baseto.utils.Constants.Companion.appLang
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val okHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(
        Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("lang", appLang?:"en")
            return@Interceptor chain.proceed(builder.build())
        }
    )
}.build()
private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.MAIN_URL).client(okHttpClient).build()

object RetrofitInstance {
    val retrofitInstance: RetrofitCalls by lazy { retrofit.create(RetrofitCalls::class.java) }
}