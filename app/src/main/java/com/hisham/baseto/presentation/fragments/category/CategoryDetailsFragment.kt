package com.hisham.baseto.presentation.fragments.category

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
import androidx.recyclerview.widget.GridLayoutManager
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentCategoryDetailsBinding
import com.hisham.baseto.domain.repository.CategoriesRepository
import com.hisham.baseto.domain.repository.HomeRepository
import com.hisham.baseto.domain.viewmodels.categories.CategoriesViewModel
import com.hisham.baseto.domain.viewmodels.categories.CategoriesViewModelFactory
import com.hisham.baseto.domain.viewmodels.home.HomeViewModel
import com.hisham.baseto.domain.viewmodels.home.HomeViewModelFactory
import com.hisham.baseto.presentation.adapters.CategoryProductsListAdapter
import com.hisham.baseto.presentation.fragments.main.MainFragmentDirections

class CategoryDetailsFragment : Fragment() {
    private val homeViewModel: HomeViewModel by lazy {
        val application = requireNotNull(this.activity).application
        //val database = BasetoDatabase.initDatabase(application.applicationContext).dao
        val repo = HomeRepository(requireActivity())
        val viewModelFactory = HomeViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }
    private val viewModel: CategoriesViewModel by lazy {
        val application = requireNotNull(this.activity).application
        //val database = BasetoDatabase.initDatabase(application.applicationContext).dao
        val repo = CategoriesRepository()
        val viewModelFactory = CategoriesViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(requireActivity(), viewModelFactory).get(CategoriesViewModel::class.java)
    }
    private lateinit var productsAdapter: CategoryProductsListAdapter
    lateinit var binding: FragmentCategoryDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_details, container, false)

        initProductsAdapter()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val catTitle = CategoryDetailsFragmentArgs.fromBundle(requireArguments()).title
        binding.toolbar.title = catTitle
        binding.toolbar.setNavigationIcon(R.drawable.arrow_back_ios_new)
        binding.toolbar.navigationIcon?.setTint(resources.getColor(R.color.white))
        binding.toolbar.setNavigationOnClickListener {
            view?.findNavController()?.navigateUp()
        }
        return binding.root

    }

    private fun initProductsAdapter() {

        productsAdapter =
            CategoryProductsListAdapter(onClickListener = CategoryProductsListAdapter.OnClickListener {
                homeViewModel.getProductData(it.id.toString())
                viewModel.navigateToProductDetails(it)
            })
        binding.categoryProductsList.adapter = productsAdapter
        binding.categoryProductsList.layoutManager = GridLayoutManager(this.context, 2);
        viewModel.categoryDetails.observe(viewLifecycleOwner, Observer {
            it?.let { prods ->
                Log.i("CategoryFragmentDetails", prods.detailsData?.productData!![0].name!!)
                productsAdapter.setProducts(prods.detailsData?.productData!!)
                productsAdapter.notifyDataSetChanged()

            }

        })

        viewModel.navigateToSelectedProductData.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                view?.findNavController()?.navigate(CategoryDetailsFragmentDirections.actionCategoryDetailsFragmentToProductDetailsFragment())
                viewModel.navigateToProductDetailsComplete()
            }
        })
    }
}