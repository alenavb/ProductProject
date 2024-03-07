package com.example.testvkproject.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import com.example.testvkproject.databinding.FragmentDetailsBinding
import com.example.testvkproject.domain.Product

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var mBind: FragmentDetailsBinding
    lateinit var currentProduct: Product

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBind = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        currentProduct = arguments?.getSerializable("getProduct") as Product
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        Glide.with(MAIN)
            .load(currentProduct.thumbnail)
            .fitCenter()
            .into(mBind.imgDetailed)

        val price = "${currentProduct.price.toString()} $"

        mBind.tvTitleDetailed.text = currentProduct.title
        mBind.tvCategoryDetailed.text = currentProduct.category
        mBind.tvDescriptionDetailed.text = currentProduct.description
        mBind.tvPriceDetailed.text = price

        if (currentProduct == null) {
            mBind.includeNoSignal.linearNoInternet.visibility = View.VISIBLE
        } else {
            mBind.includeNoSignal.linearNoInternet.visibility = View.GONE
        }
    }
}