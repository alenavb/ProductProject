package com.example.testvkproject.ui.main.adapter


import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testvkproject.R
import com.example.testvkproject.databinding.ItemProductBinding
import com.example.testvkproject.domain.Product
import com.example.testvkproject.ui.main.MainFragment


class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var productList: List<Product> = emptyList()

    fun submitList(newList: List<Product>) {
        val diffResult = DiffUtil.calculateDiff(ProductDiffCallback(productList, newList))
        productList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
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

                buttonNext.setOnClickListener {
                    MainFragment.clickProduct(productList[position])
                }

            }
        }
    }
}
