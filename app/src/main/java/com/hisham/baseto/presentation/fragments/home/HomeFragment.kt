package com.hisham.baseto.presentation.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.R
import com.hisham.baseto.data.database.BasetoDatabase
import com.hisham.baseto.databinding.FragmentHomeBinding
import com.hisham.baseto.databinding.FragmentLoginBinding
import com.hisham.baseto.domain.repository.HomeRepository
import com.hisham.baseto.domain.repository.UserRepository
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModel
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModelFactory
import com.hisham.baseto.domain.viewmodels.home.HomeViewModel
import com.hisham.baseto.domain.viewmodels.home.HomeViewModelFactory
import com.hisham.baseto.presentation.adapters.SliderAdapter
import com.smarteist.autoimageslider.SliderView

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        val application = requireNotNull(this.activity).application
        //val database = BasetoDatabase.initDatabase(application.applicationContext).dao
        val repo = HomeRepository(requireActivity())
        val viewModelFactory = HomeViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val adapter = SliderAdapter(this.context,viewModel.imageList)
        binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        binding.slider.setSliderAdapter(adapter)
        binding.slider.isAutoCycle = true;
        binding.lifecycleOwner = this
        return binding.root
    }
}