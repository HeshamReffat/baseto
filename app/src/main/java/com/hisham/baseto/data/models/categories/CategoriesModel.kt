package com.hisham.baseto.data.models.categories

import com.google.gson.annotations.SerializedName


data class CategoriesModel(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: Data? = Data()

)

data class Data(

    @SerializedName("current_page") var currentPage: Int? = null,
    @SerializedName("data") var catData: ArrayList<CategoryData> = arrayListOf(),
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

data class CategoryData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null

)