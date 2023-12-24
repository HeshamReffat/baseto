package com.hisham.baseto.domain.repository

import android.util.Log
import com.hisham.baseto.data.api.RetrofitInstance
import com.hisham.baseto.data.models.cart.CartDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CartRepository {
    suspend fun addToCart(id: String) {
        withContext(Dispatchers.IO) {
            val response =
                RetrofitInstance.retrofitInstance.addToCart(data = mapOf("product_id" to id))
            Log.i("AddToCartResponse", (response.body()?.get("message") ?: "ss").toString())
        }
    }

    suspend fun getCart(): Response<CartDataModel> {
        var response: Response<CartDataModel>
        withContext(Dispatchers.IO) {
            response = RetrofitInstance.retrofitInstance.getCart()
            Log.i("GetCartResponse", (response.body()?.message ?: "no Message").toString())
        }
        return response
    }
}