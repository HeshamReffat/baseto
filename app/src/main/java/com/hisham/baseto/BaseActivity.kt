package com.hisham.baseto

import android.content.Context
import android.content.ContextWrapper
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.hisham.baseto.utils.Constants.Companion.appLang
import com.hisham.baseto.utils.ContextUtils
import java.util.Locale

open class BaseActivity: AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(newBase, Locale(appLang?:"en"))
        super.attachBaseContext(localeUpdatedContext)
    }
}

