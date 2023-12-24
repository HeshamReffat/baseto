package com.hisham.baseto.data.models.cart

import com.google.gson.annotations.SerializedName

data class CartDataModel(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: Data? = Data()
)

data class Data(

    @SerializedName("cart_items") var cartItems: ArrayList<CartItems> = arrayListOf(),
    @SerializedName("sub_total") var subTotal: Int? = null,
    @SerializedName("total") var total: Int? = null

)

data class CartItems(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("quantity") var quantity: Int? = null,
    @SerializedName("product") var product: Product? = Product()

)

data class Product(

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