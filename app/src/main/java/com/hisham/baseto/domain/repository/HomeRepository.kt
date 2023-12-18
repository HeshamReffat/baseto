package com.hisham.baseto.domain.repository

import android.app.Activity
import android.util.Log
import com.hisham.baseto.data.api.RetrofitInstance
import com.hisham.baseto.data.models.banners.BannersModel
import com.hisham.baseto.data.models.home.HomeDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class HomeRepository(private val activity: Activity) {
    suspend fun getHomeBanners(): Response<BannersModel> {
        var response: Response<BannersModel>
        withContext(Dispatchers.IO) {
            response = RetrofitInstance.retrofitInstance.getHomeBanners()
        }
        response.body()?.data?.get(0)?.image?.let { Log.d("HomeBanners", it) }
        return response
    }

    suspend fun getHomeData(): Response<HomeDataModel> {
        var response: Response<HomeDataModel>
        withContext(Dispatchers.IO) {
            response = RetrofitInstance.retrofitInstance.getHomeData()
        }
        return response
    }
}