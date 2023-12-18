package com.hisham.baseto.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hisham.baseto.R
import com.hisham.baseto.data.models.banners.ImageData
import com.hisham.baseto.data.models.home.Banners
import com.hisham.baseto.databinding.SliderLayoutBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter() :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    // list for storing urls of images.
    private val mSliderItems = ArrayList<Banners>()

    // Constructor

    fun setBanners(banners: List<Banners>) {
        mSliderItems.clear()
        mSliderItems.addAll(banners)

    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val inflate: SliderLayoutBinding =
            DataBindingUtil.inflate(inflater,R.layout.slider_layout, parent, false)
        return SliderAdapterViewHolder(inflate)
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        // Glide is use to load image
        // from url in your imageview.
        viewHolder.bind(mSliderItems[position])
    }

    // this method will return
    // the count of our list.
    override fun getCount(): Int {
        return mSliderItems.size
    }

    class SliderAdapterViewHolder(private val binding: SliderLayoutBinding) : ViewHolder(binding.root) {
        // Adapter class for initializing
        // the views of our slider view.

        fun bind(image: Banners) {
            Glide.with(binding.myImageView.context)
                .load(image.image)
                .fitCenter()
                .into(binding.myImageView)
        }
    }
}
