package com.example.testvkproject.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import com.example.testvkproject.databinding.ItemProductBinding
import com.example.testvkproject.domain.Product
import com.example.testvkproject.ui.main.MainFragment

class MainAdapter
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

        init {
            binding.buttonNext.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = getItem(position)
                    product?.let { MainFragment.clickProduct(it) }
                }
            }
        }

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
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageItem)
            }
        }
    }

}