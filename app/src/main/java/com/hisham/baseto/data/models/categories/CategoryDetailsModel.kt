package com.hisham.baseto.data.models.categories

import com.google.gson.annotations.SerializedName


data class CategoryDetailsModel(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var detailsData: DetailsData? = DetailsData()

)

data class DetailsData(

    @SerializedName("current_page") var currentPage: Int? = null,
    @SerializedName("data") var productData: ArrayList<ProductData> = arrayListOf(),
    @SerializedName("first_page_url") var firstPageUrl: String? = null,
    @SerializedName("from") var from: Int? = null,
    @SerializedName("last_page") var lastPage: Int? = null,
    @SerializedName("last_page_url") var lastPageUrl: String? = null,
    @SerializedName("next_page_url") var nextPageUrl: String? = null,
    @SerializedName("path") var path: String? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("prev_page_url") var prevPageUrl: String? = null,
    @SerializedName("to") var to: Int? = null,
    @SerializedName("total") var total: Int? = null

)

data class ProductData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("price") var price: Number? = null,
    @SerializedName("old_price") var oldPrice: Number? = null,
    @SerializedName("discount") var discount: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("images") var images: ArrayList<String> = arrayListOf(),
    @SerializedName("in_favorites") var inFavorites: Boolean? = null,
    @SerializedName("in_cart") var inCart: Boolean? = null

)