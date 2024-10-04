package com.example.testvkproject.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.details_info.R
import com.example.details_info.databinding.FragmentDetailsBinding
import com.example.testvkproject.domain.model.Product

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var mBind: FragmentDetailsBinding
    private var currentProduct: Product? = null
    private var searchProducts: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBind = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        currentProduct = arguments?.getSerializable("product") as? Product
        searchProducts = arguments?.getSerializable("productSearch") as? Product

        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        currentProduct?.let { product ->
            Glide.with(this)
                .load(product.thumbnail)
                .fitCenter()
                .into(mBind.imgDetailed)

            val price = "${product.price.toString()} $"

            mBind.tvTitleDetailed.text = product.title
            mBind.tvCategoryDetailed.text = product.category
            mBind.tvDescriptionDetailed.text = product.description
            mBind.tvPriceDetailed.text = price
        }

        searchProducts?.let { product ->
            Glide.with(this)
                .load(product.thumbnail)
                .fitCenter()
                .into(mBind.imgDetailed)

            val price = "${product.price.toString()} $"

            mBind.tvTitleDetailed.text = product.title
            mBind.tvCategoryDetailed.text = product.category
            mBind.tvDescriptionDetailed.text = product.description
            mBind.tvPriceDetailed.text = price
        }
    }
}