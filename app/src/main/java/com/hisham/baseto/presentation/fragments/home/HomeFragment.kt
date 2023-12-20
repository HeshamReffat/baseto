package com.hisham.baseto.presentation.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentHomeBinding
import com.hisham.baseto.domain.repository.CategoriesRepository
import com.hisham.baseto.domain.repository.HomeRepository
import com.hisham.baseto.domain.viewmodels.categories.CategoriesViewModel
import com.hisham.baseto.domain.viewmodels.categories.CategoriesViewModelFactory
import com.hisham.baseto.domain.viewmodels.home.HomeViewModel
import com.hisham.baseto.domain.viewmodels.home.HomeViewModelFactory
import com.hisham.baseto.presentation.adapters.HomeCategoriesListAdapter
import com.hisham.baseto.presentation.adapters.HomeProductsListAdapter
import com.hisham.baseto.presentation.adapters.SliderAdapter
import com.hisham.baseto.presentation.fragments.main.MainFragmentDirections
import com.smarteist.autoimageslider.SliderView

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        val application = requireNotNull(this.activity).application
        //val database = BasetoDatabase.initDatabase(application.applicationContext).dao
        val repo = HomeRepository(requireActivity())
        val viewModelFactory = HomeViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }
    private val catViewModel: CategoriesViewModel by lazy {
        val application = requireNotNull(this.activity).application
        //val database = BasetoDatabase.initDatabase(application.applicationContext).dao
        val repo = CategoriesRepository()
        val viewModelFactory = CategoriesViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(requireActivity(), viewModelFactory).get(CategoriesViewModel::class.java)
    }
    lateinit var binding: FragmentHomeBinding
    private lateinit var bannersAdapter: SliderAdapter
    private lateinit var catAdapter: HomeCategoriesListAdapter
    private lateinit var productsAdapter: HomeProductsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initSliderAdapter()
        initCategoriesAdapter()
        initProductsAdapter()
        return binding.root

    }

    private fun initSliderAdapter() {
        bannersAdapter = SliderAdapter()
        binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        binding.slider.setSliderAdapter(bannersAdapter)
        binding.slider.scrollTimeInSec = 3
        binding.slider.isAutoCycle = true
        binding.slider.startAutoCycle()
        displayBanners()

    }

    private fun displayBanners() {
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            Log.i("arrayImagesList", it[0].image ?: "NoImage")
            bannersAdapter.setBanners(it)
            bannersAdapter.notifyDataSetChanged()
        })
    }

    private fun initCategoriesAdapter() {

        catAdapter =
            HomeCategoriesListAdapter(onClickListener = HomeCategoriesListAdapter.OnClickListener {
                catViewModel.getCategoryDetails(it.id.toString())
                catViewModel.navigateToCategoryDetails(it)
            })
        binding.homeCategoriesList.adapter = catAdapter
        binding.homeCategoriesList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);
        catViewModel.homeCategories.observe(viewLifecycleOwner, Observer {
            it.data?.let { cats -> catAdapter.setCategories(cats.catData) }
            catAdapter.notifyDataSetChanged()
        })

        catViewModel.navigateToSelectedCategory.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                view?.findNavController()?.navigate(MainFragmentDirections.actionMainFragmentToCategoryDetailsFragment(it.name?:""))
                catViewModel.navigateToCategoryDetailsComplete()
            }
        })
    }

    private fun initProductsAdapter() {

        productsAdapter =
            HomeProductsListAdapter(onClickListener = HomeProductsListAdapter.OnClickListener {
                viewModel.getProductData(it.id.toString())
                viewModel.navigateToProductDetails(it)
            })
        binding.homeProductsList.adapter = productsAdapter
        binding.homeProductsList.layoutManager = GridLayoutManager(this.context, 2);
        viewModel.productsList.observe(viewLifecycleOwner, Observer {
            it?.let { cats -> productsAdapter.setProducts(it) }
            productsAdapter.notifyDataSetChanged()
        })

        viewModel.navigateToSelectedProduct.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                view?.findNavController()?.navigate(MainFragmentDirections.actionMainFragmentToProductDetailsFragment())
                viewModel.navigateToProductDetailsComplete()
            }
        })
    }
}