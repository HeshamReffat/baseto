package com.hisham.baseto.domain.viewmodels.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.domain.repository.CartRepository

class CartViewModelFactory(val repo: CartRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repo) as T
        }
        throw IllegalArgumentException("Wrong View Model")
    }
}