package com.hisham.baseto.presentation.fragments.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hisham.baseto.R
import com.hisham.baseto.databinding.FragmentCartBinding
import com.hisham.baseto.domain.repository.CartRepository
import com.hisham.baseto.domain.viewmodels.cart.CartViewModel
import com.hisham.baseto.domain.viewmodels.cart.CartViewModelFactory
import com.hisham.baseto.presentation.adapters.CartProductsListAdapter
import com.hisham.baseto.presentation.adapters.HomeCategoriesListAdapter
import com.hisham.baseto.presentation.fragments.main.MainFragmentDirections

class CartFragment : Fragment() {
    lateinit var binding:FragmentCartBinding
    private lateinit var cartProductsAdapter:CartProductsListAdapter
    val cartViewModel:CartViewModel by lazy{
        val application = requireActivity().application
        val repository = CartRepository()
        val factory = CartViewModelFactory(repository,application)
        ViewModelProvider(requireActivity(),factory)[CartViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)
        // Inflate the layout for this fragment
        initCartProductsAdapter()
        binding.viewModel = cartViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    private fun initCartProductsAdapter() {

        cartProductsAdapter =
            CartProductsListAdapter(onClickListener = CartProductsListAdapter.OnClickListener {
            },cartViewModel)
        binding.cartProducts.adapter = cartProductsAdapter
        binding.cartProducts.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
        cartViewModel.cartData.observe(viewLifecycleOwner, Observer {
            it.data?.let { cart -> cartProductsAdapter.setProducts(cart.cartItems) }
            cartProductsAdapter.notifyDataSetChanged()
        })

//        categoryViewModel.navigateToSelectedCategory.observe(viewLifecycleOwner, Observer {
//            if (null != it) {
//                view?.findNavController()?.navigate(
//                    MainFragmentDirections.actionMainFragmentToCategoryDetailsFragment(
//                        it.name ?: ""
//                    )
//                )
//                categoryViewModel.navigateToCategoryDetailsComplete()
//            }
//        })
    }
}