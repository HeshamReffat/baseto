package com.hisham.baseto.data.api

import com.hisham.baseto.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.MAIN_URL).build()

object RetrofitInstance {
    val retrofitInstance: RetrofitCalls by lazy { retrofit.create(RetrofitCalls::class.java) }
}