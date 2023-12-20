package com.hisham.baseto.presentation.fragments.product

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentHomeBinding
import com.hisham.baseto.databinding.FragmentProductDetailsBinding
import com.hisham.baseto.domain.repository.HomeRepository
import com.hisham.baseto.domain.viewmodels.home.HomeViewModel
import com.hisham.baseto.domain.viewmodels.home.HomeViewModelFactory
import com.hisham.baseto.presentation.fragments.category.CategoryDetailsFragmentArgs


class ProductDetailsFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        val application = requireNotNull(this.activity).application
        //val database = BasetoDatabase.initDatabase(application.applicationContext).dao
        val repo = HomeRepository(requireActivity())
        val viewModelFactory = HomeViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }
    lateinit var binding: FragmentProductDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)
       // val navFrom = ProductDetailsFragmentArgs.fromBundle(requireArguments()).from
        viewModel.productsInfo.observe(viewLifecycleOwner, Observer {
            binding.apply {
                productName.text = it.data?.name
                productName.ellipsize = TextUtils.TruncateAt.END;
                productName.maxLines = 2;
                productPrice.text = "${it.data?.price?.toDouble()} EGP"
                productDesc.text = it.data?.description
                Glide.with(requireContext()).load(it.data?.image).into(productImageView)
            }
        })

        binding.toolbar.setNavigationIcon(R.drawable.arrow_back_ios_new)
        binding.toolbar.navigationIcon?.setTint(resources.getColor(R.color.white))
        binding.toolbar.setNavigationOnClickListener {
            view?.findNavController()?.navigateUp()
//            if(navFrom == "home") {
//
//
//                view?.findNavController()
//                    ?.navigate(R.id.action_productDetailsFragment_to_mainFragment)
//            }else{
//                view?.findNavController()
//                    ?.navigate(R.id.action_productDetailsFragment_to_categoryDetailsFragment)
//            }
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}