package com.hisham.baseto.utils

class Constants {
    enum class ApiStatus { LOADING, ERROR, DONE }
    companion object {
        var userToken: String? = null
        var appLang:String = "ar"
    }
}