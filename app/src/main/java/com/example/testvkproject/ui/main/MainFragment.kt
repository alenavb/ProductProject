package com.example.testvkproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testvkproject.R
import com.example.testvkproject.databinding.FragmentMainBinding
import com.example.testvkproject.ui.utils.appComponent
import com.example.testvkproject.ui.main.adapter.MainAdapter
import com.example.testvkproject.ui.main.adapter.SearchAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mBind: FragmentMainBinding
    lateinit var adapter : MainAdapter
    lateinit var searchAdapter: SearchAdapter

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
        adapter = MainAdapter(requireActivity().supportFragmentManager)
        searchAdapter = SearchAdapter(requireActivity().supportFragmentManager)

        setupRecyclerView()
        observeProducts()

        adapter.addLoadStateListener { loadState ->
            mBind.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            mBind.prgBarMovies.isVisible = loadState.source.refresh is LoadState.Loading
        }

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
            mBind.includeNoResult.linearNoResult.visibility = View.GONE
            mBind.searchVIew.setQuery("", false)
            setupRecyclerView()
            observeProducts()
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
                    setupRecyclerViewSearchProducts()
                    searchAdapter.submitList(products)
                }
            } else {
                mBind.includeNoResult.linearNoResult.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        mBind.recyclerView.adapter = adapter
        mBind.recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setupRecyclerViewSearchProducts() {
        mBind.recyclerView.adapter = searchAdapter
        mBind.recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeProducts() {
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productsList.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    fun inject() {
        requireContext().appComponent().inject(this)
    }

}