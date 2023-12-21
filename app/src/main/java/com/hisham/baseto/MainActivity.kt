package com.hisham.baseto

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hisham.baseto.utils.Constants
import com.hisham.baseto.utils.Constants.Companion.appLang
import com.hisham.baseto.utils.ContextUtils
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = this.getPreferences(Context.MODE_PRIVATE) ?: return
        appLang = sharedPreferences.getString("appLang", "en")?:"en"
        if(appLang == "ar") {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL;
        }else{
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR;
        }
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        setApplicationLanguage(appLang?:"en")
    }

        override fun onConfigurationChanged(newConfig: Configuration) {
        baseContext.resources.updateConfiguration(newConfig,baseContext.resources.displayMetrics)
        setContentView(R.layout.activity_main)
        super.onConfigurationChanged(newConfig)
    }
    private fun setApplicationLanguage(newLanguage: String) {
        val activityRes = resources
        val activityConf = activityRes.configuration
        val newLocale = Locale(newLanguage)
        activityConf.setLocale(newLocale)
        activityRes.updateConfiguration(activityConf, activityRes.displayMetrics)

        val applicationRes = applicationContext.resources
        val applicationConf = applicationRes.configuration
        applicationConf.setLocale(newLocale)
        applicationRes.updateConfiguration(
            applicationConf,
            applicationRes.displayMetrics
        )
    }

}