package com.example.testvkproject.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import com.example.testvkproject.databinding.FragmentMainBinding
import com.example.testvkproject.domain.Product
import com.example.testvkproject.ui.main.adapter.MainAdapter
import com.google.android.material.search.SearchBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var mBind: FragmentMainBinding
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
        searchWords()
        observeProducts()
        setupBackButtonListener()

    }

    private fun setupBackButtonListener() {
        requireActivity().onBackPressedDispatcher
            loadProducts("")
        }

    private fun searchWords() {
        mBind.searchVIew.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    loadProducts(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        mBind.searchVIew.setOnCloseListener {
            loadProducts("")
            mBind.searchVIew.setQuery("", false)
            false
        }
    }

    private fun loadProducts(query: String) {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.searchProducts(query)
        viewModel.myProductSearch.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val products = response.body()?.products ?: emptyList()
                if (products.isEmpty()) {
                    mBind.includeNoResult.linearNoResult.visibility = View.VISIBLE
                } else {
                    mBind.includeNoResult.linearNoResult.visibility = View.GONE
                    adapter.submitList(products)
                }
            } else {
                mBind.includeNoResult.linearNoResult.visibility = View.VISIBLE
            }
        }
    }

    private fun init() {
        mBind.recyclerView.adapter = adapter
        mBind.recyclerView.layoutManager = GridLayoutManager(context, 2)
        mBind.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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