package com.hisham.baseto.domain.repository

import android.util.Log
import com.hisham.baseto.data.api.RetrofitInstance
import com.hisham.baseto.data.models.categories.CategoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CategoriesRepository {

    suspend fun getHomeCategories(): Response<CategoriesModel> {
        var response: Response<CategoriesModel>?
        try {

            withContext(Dispatchers.IO) {
                response = RetrofitInstance.retrofitInstance.getCategories()
                if (response?.body()?.status == true) {
                    response?.body()?.message?.let { Log.i("categoriesResponse", it) }
                }
            }

        } catch (e: Exception) {
            response = null
        }
        return response!!
    }
}