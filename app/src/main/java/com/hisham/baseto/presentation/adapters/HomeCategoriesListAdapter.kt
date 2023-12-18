package com.hisham.baseto.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hisham.baseto.R
import com.hisham.baseto.data.models.categories.CategoryData
import com.hisham.baseto.databinding.CategoryItemLayoutBinding

class HomeCategoriesListAdapter (private val onClickListener: OnClickListener): ListAdapter<CategoryData, HomeCategoriesListAdapter.ViewHolder>(DiffCallBack) {
    private val categories = ArrayList<CategoryData>()
    fun setCategories(cats:List<CategoryData>){
        categories.clear()
        categories.addAll(cats)

    }
    class OnClickListener(val clickListener:(cayegory: CategoryData)->Unit){
        fun onClick(cayegory: CategoryData) = clickListener(cayegory)
    }
    class ViewHolder(private val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryData) {
            binding.catText.text = category.name
            Glide.with(binding.catImage.context).load(category.image).into(binding.catImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CategoryItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.category_item_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
    companion object DiffCallBack : DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}