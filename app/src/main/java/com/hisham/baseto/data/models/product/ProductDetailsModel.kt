package com.hisham.baseto.data.models.product

import com.google.gson.annotations.SerializedName


data class ProductDetailsModel(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: Data? = Data()

)

data class Data(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("price") var price: Number? = null,
    @SerializedName("old_price") var oldPrice: Number? = null,
    @SerializedName("discount") var discount: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("in_favorites") var inFavorites: Boolean? = null,
    @SerializedName("in_cart") var inCart: Boolean? = null,
    @SerializedName("images") var images: ArrayList<String> = arrayListOf()

)