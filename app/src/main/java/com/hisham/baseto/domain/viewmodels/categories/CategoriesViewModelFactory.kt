package com.hisham.baseto.domain.viewmodels.categories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.domain.repository.CategoriesRepository

class CategoriesViewModelFactory(
    private val repo: CategoriesRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            return CategoriesViewModel(repo, context) as T
        }
        throw IllegalArgumentException("wrong viewModel")
    }
}