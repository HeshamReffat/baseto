package com.hisham.baseto.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentOnboardBinding


class OnboardFragment : Fragment() {
    lateinit var binding: FragmentOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboard, container, false)
        // Inflate the layout for this fragment
        binding.nextBtn.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_loginRegisterFragment)
        }
        return binding.root
    }
}