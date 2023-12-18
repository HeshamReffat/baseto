package com.hisham.baseto.data.models.home

import com.google.gson.annotations.SerializedName


data class HomeDataModel(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: Data? = Data()

)

data class Data(

    @SerializedName("banners") var banners: ArrayList<Banners> = arrayListOf(),
    @SerializedName("products") var products: ArrayList<Products> = arrayListOf(),
    @SerializedName("ad") var ad: String? = null

)

data class Banners(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("product") var product: String? = null

)

data class Products(

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