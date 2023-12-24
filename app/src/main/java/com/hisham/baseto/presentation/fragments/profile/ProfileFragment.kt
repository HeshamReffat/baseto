package com.hisham.baseto.presentation.fragments.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hisham.baseto.MainActivity
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentProductDetailsBinding
import com.hisham.baseto.databinding.FragmentProfileBinding
import com.hisham.baseto.utils.Constants
import com.hisham.baseto.utils.Constants.Companion.appLang

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.changeLangText.setOnClickListener {
            if(appLang == "en") {
                sharedPreferences.edit().putString("appLang", "ar").apply()
                appLang = sharedPreferences.getString("appLang", "ar") ?: "ar"
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }else{
                sharedPreferences.edit().putString("appLang", "en").apply()
                appLang = sharedPreferences.getString("appLang", "en") ?: "en"
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
        return binding.root
    }

}