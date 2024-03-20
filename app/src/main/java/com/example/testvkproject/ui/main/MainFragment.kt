package com.example.testvkproject.ui.main

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testvkproject.App
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import com.example.testvkproject.databinding.FragmentMainBinding
import com.example.testvkproject.domain.Product
import com.example.testvkproject.ui.main.adapter.MainAdapter
import com.example.testvkproject.ui.main.adapter.SearchAdapter
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mBind: FragmentMainBinding
    private val adapter: MainAdapter = MainAdapter()
    private val searchAdapter: SearchAdapter = SearchAdapter()
    private var recyclerViewState: Parcelable? = null


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBind = FragmentMainBinding.inflate(inflater, container, false)
        return mBind.root
    }

    override fun onPause() {
        super.onPause()
        recyclerViewState = mBind.recyclerView.layoutManager?.onSaveInstanceState()
    }

    override fun onResume() {
        super.onResume()
        recyclerViewState?.let {
            mBind.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeProducts()
        searchProduct()

    }

    private fun setupRecyclerView() {
        mBind.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@MainFragment.adapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val totalItemCount = layoutManager?.itemCount ?: 0
                    val lastVisibleItemPosition =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    if (totalItemCount <= lastVisibleItemPosition + 5) {
                        viewModel.loadNextPage()
                    }
                }
            })
        }
    }

    private fun setupRecyclerViewSearchProducts() {
        mBind.recyclerView.apply {
            mBind.recyclerView.adapter = searchAdapter
            mBind.recyclerView.layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun searchProduct() {
        mBind.searchVIew.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchProducts(it)
                    observeSearchProducts()
                    setupRecyclerViewSearchProducts()
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
            observeProducts()
            setupRecyclerView()
            false
        }
    }

    private fun observeSearchProducts() {
        viewModel.searchLiveData.observe(viewLifecycleOwner) { searchProducts ->
            if (searchProducts.isEmpty()) {
                mBind.includeNoResult.linearNoResult.visibility = View.VISIBLE
            } else {
                mBind.includeNoResult.linearNoResult.visibility = View.GONE
                searchAdapter.submitList(searchProducts)
            }
        }
    }


    private fun observeProducts() {
        viewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            if (products.isEmpty()) {
                viewModel.isLoadingError.value?.let { isError ->
                    if (!isError) {
                        mBind.includeNoSignal.linearNoInternet.visibility = View.VISIBLE
                    }
                }
            } else {
                mBind.includeNoSignal.linearNoInternet.visibility = View.GONE
                adapter.submitList(products)
            }
        }

        viewModel.isLoadingError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                mBind.includeNoSignal.linearNoInternet.visibility = View.VISIBLE
            } else {
                mBind.includeNoSignal.linearNoInternet.visibility = View.GONE
            }
        }

        viewModel.loadInitialProducts()

    }

    companion object {
        fun clickProduct(model: Product) {
            val bundle = Bundle()
            bundle.putSerializable("getProduct", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
        }
    }
}

