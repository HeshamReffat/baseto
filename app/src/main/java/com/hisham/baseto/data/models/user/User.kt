package com.hisham.baseto.data.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class UserDataModel(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: User? = User()

)

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("points") var points: Int? = null,
   // @SerializedName("credit") var credit: Long? = null,
    @SerializedName("token") var token: String? = null

)