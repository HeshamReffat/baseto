package com.hisham.baseto.presentation.fragments.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentMainBinding
import com.hisham.baseto.presentation.fragments.cart.CartFragment
import com.hisham.baseto.presentation.fragments.home.HomeFragment
import com.hisham.baseto.presentation.fragments.profile.ProfileFragment
import com.hisham.baseto.presentation.fragments.search.SearchFragment

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        replaceFragment(HomeFragment())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        // Inflate the layout for this fragment
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.action_search -> {
                    //  context = LocaleHelper.setLocale(requireContext(), "en");

                    sharedPreferences.edit().putString("appLang", "en").apply()
                    requireContext().recreateTask()
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.action_cart -> {
                    replaceFragment(CartFragment())
                    true
                }

                R.id.action_profile -> {
                    // context = LocaleHelper.setLocale(requireContext(), "ar");
                    //  ContextUtils.updateLocale(requireContext(),  Locale("ar"))
                    sharedPreferences.edit().putString("appLang", "ar").apply()
                    requireContext().recreateTask()
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> {
                    true
                }
            }
        }
        return binding.root

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.navframeLayout, fragment)
        transaction.commit()
    }

    fun Context.recreateTask() {
        this.packageManager
            .getLaunchIntentForPackage(applicationContext.packageName)
            ?.let { intent ->
                val restartIntent = Intent.makeRestartActivityTask(intent.component)
                this.startActivity(restartIntent)
                Runtime.getRuntime().exit(0)
            }
    }
}