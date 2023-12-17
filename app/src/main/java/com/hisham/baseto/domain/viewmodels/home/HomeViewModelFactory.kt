package com.hisham.baseto.domain.viewmodels.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.domain.repository.HomeRepository
class HomeViewModelFactory(private val repo:HomeRepository,private  val context:Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return  HomeViewModel(repo,context) as T
        }
        throw  IllegalArgumentException("Wrong Viewmodel")
    }
}