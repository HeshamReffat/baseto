package com.hisham.baseto.presentation.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hisham.baseto.R
import com.hisham.baseto.data.models.home.Products
import com.hisham.baseto.databinding.ProductItemLayoutBinding

class HomeProductsListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Products, HomeProductsListAdapter.ViewHolder>(DiffUtilCallBack) {

    private val products = ArrayList<Products>()
    fun setProducts(prods: List<Products>) {
        products.clear()
        products.addAll(prods)

    }

    class OnClickListener(val onClickListener: (product: Products) -> Unit) {
        fun onClick(product: Products) = onClickListener(product)
    }

    class ViewHolder(val binding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            binding.prodName.text = product.name
            binding.prodPrice.text = "${product.price?.toDouble()} EGP"
            binding.prodName.ellipsize = TextUtils.TruncateAt.END;
            binding.prodName.maxLines = 2;
            Glide.with(binding.prodImage.context).load(product.image).into(binding.prodImage)
        }
    }

    companion object DiffUtilCallBack : DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ProductItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.product_item_layout, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }
}