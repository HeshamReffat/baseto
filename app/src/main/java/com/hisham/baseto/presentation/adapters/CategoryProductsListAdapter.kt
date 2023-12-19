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
import com.hisham.baseto.databinding.CategoryProductItemLayoutBinding
import com.hisham.baseto.databinding.ProductItemLayoutBinding

class CategoryProductsListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProductData, CategoryProductsListAdapter.ViewHolder>(DiffUtilCallBack) {

    private val products = ArrayList<ProductData>()
    fun setProducts(prods: List<ProductData>) {
        products.clear()
        products.addAll(prods)
        Log.i("adapterCat",products[0].image?:"No Image")
    }

    class OnClickListener(val onClickListener: (productData: ProductData) -> Unit) {
        fun onClick(productData: ProductData) = onClickListener(productData)
    }

    class ViewHolder(val binding: CategoryProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductData) {
            binding.prodName.text = product.name
            binding.prodPrice.text = "${product.price?.toDouble()} EGP"
            binding.prodName.ellipsize = TextUtils.TruncateAt.END;
            binding.prodName.maxLines = 2;
            Glide.with(binding.prodImage.context).load(product.image).into(binding.prodImage)
        }
    }

    companion object DiffUtilCallBack : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CategoryProductItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.category_product_item_layout, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(products[position])
        }
    }
}