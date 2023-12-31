package com.hisham.baseto.presentation.adapters

import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hisham.baseto.utils.Constants

@BindingAdapter("textFontWeight")
fun TextView.textFontWeight(int: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        typeface = Typeface.create(typeface, int, false)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(loadingStatus: ProgressBar, status: Constants.ApiStatus?) {
    when (status) {
        Constants.ApiStatus.LOADING -> {
            loadingStatus.visibility = View.VISIBLE

        }

        Constants.ApiStatus.ERROR -> {
            loadingStatus.visibility = View.GONE

        }

        Constants.ApiStatus.DONE -> {
            loadingStatus.visibility = View.GONE

        }

        else -> {}
    }
}

@BindingAdapter("buttonStatus")
fun bindButtonStatus(button: Button, status: Constants.ApiStatus?) {
    when (status) {
        Constants.ApiStatus.LOADING -> {

            button.visibility = View.GONE
        }

        Constants.ApiStatus.ERROR -> {

            button.visibility = View.VISIBLE
        }

        Constants.ApiStatus.DONE -> {

            button.visibility = View.VISIBLE
        }

        else -> {}
    }
}

@BindingAdapter("listStatus")
fun bindListStatus(list: RecyclerView, status: Constants.ApiStatus?) {
    when (status) {
        Constants.ApiStatus.LOADING -> {

            list.visibility = View.GONE
        }

        Constants.ApiStatus.ERROR -> {

            list.visibility = View.VISIBLE
        }

        Constants.ApiStatus.DONE -> {

            list.visibility = View.VISIBLE
        }

        else -> {}
    }
}
@BindingAdapter("productInfo")
fun bindDetailsStatus(layout: ScrollView, status: Constants.ApiStatus?) {
    when (status) {
        Constants.ApiStatus.LOADING -> {

            layout.visibility = View.GONE
        }

        Constants.ApiStatus.ERROR -> {

            layout.visibility = View.VISIBLE
        }

        Constants.ApiStatus.DONE -> {

            layout.visibility = View.VISIBLE
        }

        else -> {}
    }
}
@BindingAdapter("viewStatus")
fun bindDetailsStatus(layout: ConstraintLayout, status: Constants.ApiStatus?) {
    when (status) {
        Constants.ApiStatus.LOADING -> {

            layout.visibility = View.GONE
        }

        Constants.ApiStatus.ERROR -> {

            layout.visibility = View.VISIBLE
        }

        Constants.ApiStatus.DONE -> {

            layout.visibility = View.VISIBLE
        }

        else -> {}
    }
}