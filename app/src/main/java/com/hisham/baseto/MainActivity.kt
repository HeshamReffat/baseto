package com.hisham.baseto

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hisham.baseto.utils.Constants
import com.hisham.baseto.utils.Constants.Companion.appLang
import com.hisham.baseto.utils.ContextUtils
import java.util.Locale

class MainActivity : BaseActivity() {
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

}