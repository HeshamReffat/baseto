package com.hisham.baseto.presentation.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.R
import com.hisham.baseto.data.database.KelineDatabase
import com.hisham.baseto.databinding.FragmentLoginBinding
import com.hisham.baseto.domain.repository.UserRepository
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModel
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModelFactory

class LoginFragment : Fragment() {
    private val viewModel: AuthViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val database = KelineDatabase.initDatabase(application.applicationContext).dao
        val repo = UserRepository(database)
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}