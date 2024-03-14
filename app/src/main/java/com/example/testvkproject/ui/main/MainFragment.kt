package com.example.testvkproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import com.example.testvkproject.databinding.FragmentMainBinding
import com.example.testvkproject.domain.Product
import com.example.testvkproject.ui.utils.appComponent
import com.example.testvkproject.ui.main.adapter.MainAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mBind: FragmentMainBinding
    lateinit var adapter : MainAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBind = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBind = FragmentMainBinding.bind(view)
        setupRecyclerView()
        observeProducts()
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter()
        mBind.recyclerView.adapter = adapter
        mBind.recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeProducts() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productsList.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }

    fun inject() {
        requireContext().appComponent().inject(this)
    }


    companion object {
        fun clickProduct(model: Product) {
            val bundle = Bundle()
            bundle.putSerializable("getProduct", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
        }
    }
}