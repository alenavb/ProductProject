package com.example.testvkproject.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testvkproject.R
import com.example.testvkproject.databinding.FragmentDetailsBinding
import com.example.testvkproject.domain.model.Product
import com.example.testvkproject.ui.utils.appComponent

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var mBind: FragmentDetailsBinding
    lateinit var currentProduct: Product

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBind = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        currentProduct = arguments?.getSerializable("product") as Product

        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun inject() {
        requireContext().appComponent().inject(this)
    }

    private fun init() {
        Glide.with(this)
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