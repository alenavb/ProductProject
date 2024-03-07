package com.example.testvkproject.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import com.example.testvkproject.databinding.FragmentMainBinding
import com.example.testvkproject.domain.Product
import com.example.testvkproject.ui.main.adapter.MainAdapter

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var mBind: FragmentMainBinding
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }
    private var currentPage = 1
    private val itemsPerPage = 20
    private var isLoading = false
    private var totalItemCount = 0
    val MAX_LIMIT = 100

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
        init()
        observeProducts()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = mBind.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                totalItemCount = layoutManager.itemCount

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0 && totalItemCount < MAX_LIMIT
                ) {
                    currentPage++
                    loadNextPage()
                }
            }
        })

        loadProducts()
    }

    private fun loadNextPage() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getProducts(currentPage, itemsPerPage)
    }

    private fun loadProducts() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getProducts(currentPage, itemsPerPage)
    }

    private fun observeProducts() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.myProducts.observe(viewLifecycleOwner) { response ->
            isLoading = false
            if (response.isSuccessful) {
                val products = response.body()?.products ?: emptyList()
                adapter.addProducts(products)
            } else {
                mBind.includeNoSignal.linearNoInternet.visibility = View.VISIBLE
                Log.e("MainFragment", "Error: ${response.message()}")
            }
        }
    }

    companion object {
        fun clickProduct(model: Product) {
            val bundle = Bundle()
            bundle.putSerializable("getProduct", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
        }
    }
}