package com.hisham.baseto.domain.viewmodels.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.data.models.banners.ImageData
import com.hisham.baseto.data.models.categories.CategoriesModel
import com.hisham.baseto.data.models.categories.CategoryData
import com.hisham.baseto.data.models.home.Banners
import com.hisham.baseto.data.models.home.Products
import com.hisham.baseto.data.models.product.ProductDetailsModel
import com.hisham.baseto.domain.repository.HomeRepository
import com.hisham.baseto.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository, private val context: Context) :
    ViewModel() {
    private val _imageList = MutableLiveData<ArrayList<Banners>>()
    val imageList: LiveData<ArrayList<Banners>>
        get() = _imageList
    private val _productsList = MutableLiveData<ArrayList<Products>>()
    val productsList: LiveData<ArrayList<Products>>
        get() = _productsList
    private val _productsInfo = MutableLiveData<ProductDetailsModel>()
    val productsInfo: LiveData<ProductDetailsModel>
        get() = _productsInfo
    private val _navigateToSelectedProduct = MutableLiveData<Products?>()
    val navigateToSelectedProduct: LiveData<Products?>
        get() = _navigateToSelectedProduct

    private val _loading = MutableLiveData<Constants.ApiStatus?>()
    val loading: LiveData<Constants.ApiStatus?>
        get() = _loading
    init {
        getHomeData()
    }

    private fun getHomeBanners() {
        viewModelScope.launch {
            val response = repository.getHomeBanners()
            //  imageList.value = response.body()?.data!!
            Log.i("ImageIn ViewModel", imageList.value!![0].image ?: "")
        }
    }

    private fun getHomeData() {
        viewModelScope.launch {
            val response = repository.getHomeData()
            _imageList.value = response.body()?.data!!.banners
            _productsList.value = response.body()?.data!!.products
        }
    }

    fun getProductData(id: String) {
        viewModelScope.launch {
            try {
                _loading.value = Constants.ApiStatus.LOADING
                val response = repository.getProductData(id)
                _productsInfo.value = response.body()
                _loading.value = Constants.ApiStatus.DONE
            } catch (e: Exception) {
                _loading.value = Constants.ApiStatus.DONE
                Log.i("ProductDetails", e.message ?: "No Error")
            }

        }
    }

    fun navigateToProductDetails(prod: Products) {
        _navigateToSelectedProduct.value = prod
    }

    fun navigateToProductDetailsComplete() {
        _navigateToSelectedProduct.value = null
    }
}