package com.hisham.baseto.presentation.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.R
import com.hisham.baseto.data.database.BasetoDatabase
import com.hisham.baseto.databinding.FragmentLoginBinding
import com.hisham.baseto.domain.repository.UserRepository
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModel
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModelFactory
import com.hisham.baseto.presentation.fragments.main.MainFragment

class LoginFragment : Fragment() {
    private val viewModel: AuthViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val database = BasetoDatabase.initDatabase(application.applicationContext).dao
        val repo = UserRepository(database,requireActivity())
        val viewModelFactory = AuthViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
    }
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment
        viewModel.loggedIn.observe(viewLifecycleOwner, Observer {
            if(it == true){
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(this.id, MainFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
            }
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}