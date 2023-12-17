package com.hisham.baseto.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hisham.baseto.R
import com.hisham.baseto.data.models.banners.ImageData
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(context: Context?, sliderDataArrayList: ArrayList<ImageData>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    // list for storing urls of images.
    private val mSliderItems: List<ImageData>

    // Constructor
    init {
        mSliderItems = sliderDataArrayList
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, null)
        return SliderAdapterViewHolder(inflate)
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val sliderItem: ImageData = mSliderItems[position]

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
            .load(sliderItem.image)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }

    // this method will return
    // the count of our list.
    override fun getCount(): Int {
        return mSliderItems.size
    }

     class SliderAdapterViewHolder(itemView: View) : ViewHolder(itemView) {
        // Adapter class for initializing
        // the views of our slider view.
         var imageView: View
        var imageViewBackground: ImageView

        init {
            imageViewBackground = itemView.findViewById(R.id.myImageView)
            imageView = itemView
        }
    }
}
