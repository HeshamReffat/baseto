package com.hisham.baseto.domain.viewmodels.cart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.domain.repository.CartRepository

class CartViewModelFactory(val repo: CartRepository,val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repo,application) as T
        }
        throw IllegalArgumentException("Wrong View Model")
    }
}