package com.example.testvkproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.main_list.R
import com.example.main_list.databinding.FragmentMainBinding
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
        adapter = MainAdapter(requireActivity().supportFragmentManager)

        setupRecyclerView()
        observeProducts()

        adapter.addLoadStateListener { loadState ->
            mBind.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            mBind.prgBarMovies.isVisible = loadState.source.refresh is LoadState.Loading
        }


    }

    private fun setupRecyclerView() {
        mBind.recyclerView.adapter = adapter
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

}