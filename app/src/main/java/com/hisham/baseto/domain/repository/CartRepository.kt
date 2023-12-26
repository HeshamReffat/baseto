package com.hisham.baseto.domain.repository

import android.util.Log
import com.hisham.baseto.data.api.RetrofitInstance
import com.hisham.baseto.data.models.cart.CartDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CartRepository {
    suspend fun addToCart(id: String) :Response<Map<String,Any>>{
        var response:Response<Map<String,Any>>
        withContext(Dispatchers.IO) {
             response =
                RetrofitInstance.retrofitInstance.addToCart(data = mapOf("product_id" to id))
            Log.i("AddToCartResponse", (response.body()?.get("message") ?: "ss").toString())
        }
        return response
    }
    suspend fun updateCart(id: String,quantity:String):Response<Map<String,Any>> {
        var response:Response<Map<String,Any>>
        withContext(Dispatchers.IO) {
             response =
                RetrofitInstance.retrofitInstance.updateCart(data = mapOf("quantity" to quantity), itemId = id)
            Log.i("UpdateCartResponse", (response.body()?.get("message") ?: "ss").toString())
        }
        return response
    }
    suspend fun removeFromCart(id: String):Response<Map<String,Any>> {
        var response:Response<Map<String,Any>>
        withContext(Dispatchers.IO) {
            response =
                RetrofitInstance.retrofitInstance.removeFromCart( itemId = id)
            Log.i("RemoveFromCartResponse", (response.body()?.get("message") ?: "ss").toString())
        }
        return response
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