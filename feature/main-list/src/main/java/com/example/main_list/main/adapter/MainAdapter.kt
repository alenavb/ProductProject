package com.example.testvkproject.ui.main.adapter

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main_list.databinding.ItemProductBinding
import com.example.testvkproject.domain.model.Product


class MainAdapter(private val fragmentManager: FragmentManager)
    : PagingDataAdapter<Product, MainAdapter.ViewHolder>(differCallback) {

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

//        init {
//            binding.buttonNext.setOnClickListener {
//                val position = bindingAdapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    val product = getItem(position)
//                    product?.let {
//                        val bundle = Bundle().apply {
//                            putSerializable("product", it)
//                        }
//                        val detailsFragment = DetailsFragment().apply {
//                            arguments = bundle
//                        }
//                        fragmentManager.beginTransaction()
//                            .replace(R.id.fragmentContainerView, detailsFragment)
//                            .addToBackStack(null)
//                            .commit()
//                    }
//                }
//            }
//        }

        fun bind(item: Product) {
            binding.apply {
                tvName.text = item.title
                tvName.ellipsize = TextUtils.TruncateAt.END
                tvName.maxLines = 1

                tvDescriptionItem.text = item.description
                tvDescriptionItem.ellipsize = TextUtils.TruncateAt.END
                tvDescriptionItem.maxLines = 2

                Glide.with(itemView.context)
                    .load(item.thumbnail)
                    .into(imageItem)
            }
        }
    }

}