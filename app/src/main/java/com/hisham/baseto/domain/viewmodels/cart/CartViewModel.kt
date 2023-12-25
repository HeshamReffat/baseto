package com.hisham.baseto.domain.viewmodels.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.data.models.cart.CartDataModel
import com.hisham.baseto.domain.repository.CartRepository
import com.hisham.baseto.utils.Constants
import kotlinx.coroutines.launch

class CartViewModel(private val repo: CartRepository) : ViewModel() {
    private val _addToCartLoading = MutableLiveData<Constants.ApiStatus?>()
    val addToCartLoading: LiveData<Constants.ApiStatus?>
        get() = _addToCartLoading

    private val _cartLoading = MutableLiveData<Constants.ApiStatus?>()
    val cartLoading: LiveData<Constants.ApiStatus?>
        get() = _cartLoading

    private val _cartData = MutableLiveData<CartDataModel>()
    val cartData: LiveData<CartDataModel>
        get() = _cartData

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            try {
                _cartLoading.value = Constants.ApiStatus.LOADING
                val response = repo.getCart()
                _cartData.value = response.body()
                Log.i("GetCartDone", response.body()?.message.toString())
                _cartLoading.value = Constants.ApiStatus.DONE
            } catch (e: Exception) {
                _cartLoading.value = Constants.ApiStatus.DONE
                Log.i("GetCartError", e.message.toString())
            }
        }
    }

    fun addToCart(id: String) {
        viewModelScope.launch {
            try {
                _addToCartLoading.value = Constants.ApiStatus.LOADING
                repo.addToCart(id)
                getCart()
                _addToCartLoading.value = Constants.ApiStatus.DONE
            } catch (e: Exception) {
                _addToCartLoading.value = Constants.ApiStatus.DONE
                Log.i("AddToCartError", e.message.toString())
            }
        }
    }
}