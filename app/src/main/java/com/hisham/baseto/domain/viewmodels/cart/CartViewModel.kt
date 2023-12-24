package com.hisham.baseto.domain.viewmodels.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.domain.repository.CartRepository
import com.hisham.baseto.utils.Constants
import kotlinx.coroutines.launch

class CartViewModel(private val repo: CartRepository) : ViewModel() {
    private val _addToCartLoading = MutableLiveData<Constants.ApiStatus?>()
    val addToCartLoading: LiveData<Constants.ApiStatus?>
        get() = _addToCartLoading

    fun getCart() {
        viewModelScope.launch {
            try {

                val response = repo.getCart()
                Log.i("GetCartDone", response.body()?.message.toString())

            } catch (e: Exception) {

                Log.i("GetCartError", e.message.toString())
            }
        }
    }

    fun addToCart(id: String) {
        viewModelScope.launch {
            try {
                _addToCartLoading.value = Constants.ApiStatus.LOADING
                repo.addToCart(id)
                _addToCartLoading.value = Constants.ApiStatus.DONE
            } catch (e: Exception) {
                _addToCartLoading.value = Constants.ApiStatus.DONE
                Log.i("AddToCartError", e.message.toString())
            }
        }
    }
}