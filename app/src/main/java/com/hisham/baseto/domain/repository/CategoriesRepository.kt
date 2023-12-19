package com.hisham.baseto.domain.repository

import com.hisham.baseto.data.models.categories.CategoryDetailsModel
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
            Log.i("getCategoriesError", e.message?:"no Error")
            response = null
        }
        return response!!
    }
    suspend fun getCategoryDetails(id:String): Response<CategoryDetailsModel> {
        var response: Response<CategoryDetailsModel>?
        try {

            withContext(Dispatchers.IO) {
                response = RetrofitInstance.retrofitInstance.getCategoryDetails(id)
                if (response?.body()?.status == true) {
                    response?.body()?.message?.let { Log.i("categoryDetailsResponse", it) }
                }
            }

        } catch (e: Exception) {
            Log.i("categoryDetailsError", e.message?:"no Error")
            response = null
        }
        return response!!
    }
}