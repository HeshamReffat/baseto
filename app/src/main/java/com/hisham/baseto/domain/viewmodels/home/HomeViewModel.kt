package com.hisham.baseto.domain.viewmodels.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.baseto.data.models.banners.ImageData
import com.hisham.baseto.domain.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository, private val context: Context) :
    ViewModel() {
     var imageList: ArrayList<ImageData>

    init {
        imageList =
        getHomeBanners()
    }

    private fun getHomeBanners(): ArrayList<ImageData>{
        var images:ArrayList<ImageData> = arrayListOf(ImageData())
        viewModelScope.launch {
            val response = repository.getHomeBanners()
            images = response.body()?.data!!
        }
        return images
    }
}