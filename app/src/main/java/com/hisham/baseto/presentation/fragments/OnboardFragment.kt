package com.hisham.baseto.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentOnboardBinding
import com.hisham.baseto.presentation.fragments.main.MainFragment
import com.hisham.baseto.utils.Constants


class OnboardFragment : Fragment() {
    lateinit var binding: FragmentOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
     //   val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        Constants.userToken = sharedPreferences.getString("userToken", null)
        Log.i("HomeUserToken", Constants.userToken ?: "null")
        if (Constants.userToken != null) {
            val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_onboardfragment_to_mainFragment)

//            transaction.replace(this.id, MainFragment())
//            transaction.disallowAddToBackStack()
//            transaction.commit()
        }
        super.onCreate(savedInstanceState)
    }

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