package com.hisham.baseto.domain.viewmodels.categories

import com.hisham.baseto.data.models.categories.CategoryDetailsModel
import com.hisham.baseto.data.models.categories.ProductData
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.data.models.categories.CategoriesModel
import com.hisham.baseto.data.models.categories.CategoryData
import com.hisham.baseto.domain.repository.CategoriesRepository
import com.hisham.baseto.utils.Constants
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repo: CategoriesRepository, private val context: Context) :
    ViewModel() {
    init {
        getHomeCategories()
    }

    private val _loading = MutableLiveData<Constants.ApiStatus?>()
    val loading: LiveData<Constants.ApiStatus?>
        get() = _loading

    //Navigate to Category Details
    private val _navigateToSelectedCategory = MutableLiveData<CategoryData?>()
    val navigateToSelectedCategory: LiveData<CategoryData?>
        get() = _navigateToSelectedCategory
    private val _homeCategories = MutableLiveData<CategoriesModel>()

    //Navigate to Product Details
    private val _navigateToSelectedProductData = MutableLiveData<ProductData?>()
    val navigateToSelectedProductData: LiveData<ProductData?>
        get() = _navigateToSelectedProductData
    val homeCategories: LiveData<CategoriesModel>
        get() = _homeCategories
    private val _categoryDetails = MutableLiveData<CategoryDetailsModel>()
    val categoryDetails: LiveData<CategoryDetailsModel>
        get() = _categoryDetails

    private fun getHomeCategories() {
        viewModelScope.launch {
            val response = repo.getHomeCategories()
            _homeCategories.value = response.body()
        }
    }

    fun getCategoryDetails(id: String) {
        viewModelScope.launch {
            try {
                _loading.value = Constants.ApiStatus.LOADING

                val response = repo.getCategoryDetails(id)
                _categoryDetails.value = response.body()
                _loading.value = Constants.ApiStatus.DONE
            } catch (e: Exception) {
                Log.i("ErrorInCategoryDetails", e.message ?: "No Error")
                _loading.value = Constants.ApiStatus.DONE
            }
        }
    }

    fun navigateToCategoryDetails(cat: CategoryData) {
        _navigateToSelectedCategory.value = cat
    }

    fun navigateToCategoryDetailsComplete() {
        _navigateToSelectedCategory.value = null
    }

    fun navigateToProductDetails(prod: ProductData) {
        _navigateToSelectedProductData.value = prod
    }

    fun navigateToProductDetailsComplete() {
        _navigateToSelectedProductData.value = null
    }
}