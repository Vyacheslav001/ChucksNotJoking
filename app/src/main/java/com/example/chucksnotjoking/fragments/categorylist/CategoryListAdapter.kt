package com.example.chucksnotjoking.fragments.categorylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chucksnotjoking.R
import com.example.chucksnotjoking.data.Category
import com.example.chucksnotjoking.databinding.CategoryItemBinding

class CategoryListAdapter :
    ListAdapter<Category, CategoryListAdapter.Holder>(JokeCategoryComparator()) {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CategoryItemBinding.bind(view)

        fun bind(item: Category) = with(binding) {
            category.text = item.name
        }
    }

    class JokeCategoryComparator : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}