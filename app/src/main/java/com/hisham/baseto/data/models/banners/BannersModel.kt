package com.hisham.baseto.data.models.banners

import com.google.gson.annotations.SerializedName

data class BannersModel(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()

)

data class Data(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("product") var product: String? = null

)