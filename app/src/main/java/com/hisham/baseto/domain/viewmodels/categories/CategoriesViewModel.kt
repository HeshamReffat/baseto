package com.hisham.baseto.domain.viewmodels.categories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.data.models.categories.CategoriesModel
import com.hisham.baseto.data.models.categories.CategoryData
import com.hisham.baseto.domain.repository.CategoriesRepository
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repo: CategoriesRepository, private val context: Context) :
    ViewModel() {
    init {
        getHomeCategories()
    }

    private val _navigateToSelectedCategory = MutableLiveData<CategoryData?>()
    val navigateToSelectedCategory: LiveData<CategoryData?>
        get() = _navigateToSelectedCategory
    private val _homeCategories = MutableLiveData<CategoriesModel>()
    val homeCategories: LiveData<CategoriesModel>
        get() = _homeCategories

    private fun getHomeCategories() {
        viewModelScope.launch {
            val response = repo.getHomeCategories()
            _homeCategories.value = response.body()
        }
    }

    fun navigateToCategoryDetails(cat: CategoryData) {
        _navigateToSelectedCategory.value = cat
    }

    fun navigateToCategoryDetailsComplete() {
        _navigateToSelectedCategory.value = null
    }
}