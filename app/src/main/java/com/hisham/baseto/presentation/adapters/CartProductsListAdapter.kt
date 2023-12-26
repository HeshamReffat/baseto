package com.hisham.baseto.presentation.adapters

import com.hisham.baseto.data.models.categories.ProductData
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hisham.baseto.R
import com.hisham.baseto.data.models.cart.CartItems
import com.hisham.baseto.databinding.CartProductItemLayoutBinding
import com.hisham.baseto.databinding.CategoryProductItemLayoutBinding
import com.hisham.baseto.databinding.ProductItemLayoutBinding
import com.hisham.baseto.domain.viewmodels.cart.CartViewModel

class CartProductsListAdapter(
    private val onClickListener: OnClickListener,
    private val cartViewModel: CartViewModel
) :
    ListAdapter<CartItems, CartProductsListAdapter.ViewHolder>(DiffUtilCallBack) {

    private val items = ArrayList<CartItems>()
    fun setProducts(prods: List<CartItems>) {
        items.clear()
        items.addAll(prods)
        Log.i("adapterCart", items[0].product?.image ?: "No Image")
    }

    class OnClickListener(val onClickListener: (item: CartItems) -> Unit) {
        fun onClick(item: CartItems) = onClickListener(item)
    }

    class ViewHolder(val binding: CartProductItemLayoutBinding, val cartViewModel: CartViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        var quantity: Int = 1
        fun bind(item: CartItems) {
            quantity = item.quantity!!.toInt()
            binding.prodName.text = item.product?.name
            binding.prodPrice.text = "${item.product?.price?.toDouble()} EGP"
            binding.prodName.ellipsize = TextUtils.TruncateAt.END;
            binding.prodName.maxLines = 2;
            binding.quantity.text = quantity.toString()

            binding.addQuantity.setOnClickListener {
                quantity++
                binding.quantity.text = quantity.toString()
                cartViewModel.updateCart(item.id.toString(), quantity.toString())
            }
            binding.decQuantity.setOnClickListener {
                if (quantity == 1) {
                    return@setOnClickListener
                } else {
                    quantity--
                    binding.quantity.text = quantity.toString()
                    cartViewModel.updateCart(item.id.toString(), quantity.toString())
                }
            }
            binding.ivIcon.setOnClickListener {
                cartViewModel.removeFromCart(item.id.toString())
            }
            Glide.with(binding.prodImage.context).load(item.product?.image).into(binding.prodImage)
        }
    }

    companion object DiffUtilCallBack : DiffUtil.ItemCallback<CartItems>() {
        override fun areItemsTheSame(oldItem: CartItems, newItem: CartItems): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CartItems, newItem: CartItems): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CartProductItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.cart_product_item_layout, parent, false)

        return ViewHolder(binding, cartViewModel)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(items[position])
        }
    }
}