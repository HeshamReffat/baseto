package com.hisham.baseto.domain.viewmodels.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.data.models.banners.ImageData
import com.hisham.baseto.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository, private val context: Context) :
    ViewModel() {
     var imageList = MutableLiveData<ArrayList<ImageData>>()

    init {
        getHomeBanners()
    }

    private fun getHomeBanners(){
        viewModelScope.launch {
            val response = repository.getHomeBanners()
            imageList.value = response.body()?.data!!
            Log.i("ImageIn ViewModel", imageList.value!![0].image?:"")
        }
    }
}