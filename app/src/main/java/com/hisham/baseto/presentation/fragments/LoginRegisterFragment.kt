package com.hisham.baseto.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentLoginRegisterBinding


class LoginRegisterFragment : Fragment() {
    lateinit var binding:FragmentLoginRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_register, container, false)
        // Inflate the layout for this fragment
        binding.loginBTN.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginRegisterFragment_to_loginFragment)
        }
        binding.registerTV.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginRegisterFragment_to_registerFragment)
        }
        return binding.root
    }
}