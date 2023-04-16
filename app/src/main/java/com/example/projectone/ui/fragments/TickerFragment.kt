package com.example.projectone.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kotlindls.R
import com.example.kotlindls.databinding.TickerFragmentBinding
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.viewModel.TickerViewModel

import com.example.projectone.db.model.TickerModelDB
import com.example.projectone.ui.adapter.TickerAdapter
import kotlinx.coroutines.launch
import java.util.*

class TickerFragment : BaseFragment<TickerFragmentBinding>(){

    private lateinit var tickerViewModel: TickerViewModel
    private lateinit var tickerAdapter: TickerAdapter
    private var searchTickerList: List<TickerModelDB> = emptyList()
    private var isSearching = false

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): TickerFragmentBinding {
        return TickerFragmentBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tickerViewModel = ViewModelProvider(this)[TickerViewModel::class.java]
        tickerViewModel.getAllTickers().observe(viewLifecycleOwner) { ticker ->
            binding.apply {
                recyclerViewTicker.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewTicker.setHasFixedSize(true)

                val adapter = TickerAdapter(ticker)
                recyclerViewTicker.adapter = adapter
            }
        }

//    private fun initRv(){
//        tickerAdapter = TickerAdapter { item ->
//            lifecycleScope.launch {
//                val tickerModelDB = tickerViewModel.getTickerByName(item.)
//                val action = HomeFragmentDirections.actionNavigationHomeToTickerDetailFragment(tickerModelDB)
//                findNavController().navigate(action)
//            }
//        }
//
//
//        binding.recyclerViewTicker.apply {
//            layoutManager = GridLayoutManager(context,4)
//            adapter = tickerAdapter
//        }
//
//        lifecycleScope.launch {
//            tickerViewModel.getAllTickers().observe(viewLifecycleOwner, Observer { tickerList ->
//                searchTickerList = tickerList
//                tickerAdapter.submitList(tickerList)
//            })
//        }
//    }
//
//    private fun initListeners(){
//        binding.search.setOnClickListener {
//            if (isSearching){
//                lifecycleScope.launch {
//                    tickerViewModel.getAllTickerAvailable().observe(viewLifecycleOwner, Observer { tickerList ->
//                        tickerAdapter.submitList(tickerList)
//                    })
//                }
//                binding.search.setImageResource(R.drawable.ic_search)
//                isSearching = false
//            } else {
//                onSearchClick()
//                binding.search.setImageResource(R.drawable.ic_close)
//                isSearching = true
//            }
//        }
//    }
//
//    private fun onSearchClick(){
//        val searchView = SearchView(requireContext())
//        searchView.queryHint = "Pesquisar"
//        searchView.onActionViewExpanded()
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String): Boolean {
//                searchTicker(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                searchTicker(newText)
//                return true
//            }
//
//        })
//
//        val searchDialog = AlertDialog.Builder(requireContext())
//            .setView(searchView)
//            .create()
//
//        searchDialog.show()
//    }
//
//    private fun searchTicker(query: String){
//        val lowercaseQuery = query.lowercase(Locale.ROOT)
//        val filteredList = searchTickerList.filter { ticker ->
//            ticker.name.lowercase(Locale.ROOT).contains(lowercaseQuery)
//        }
//        tickerAdapter.submitList(filteredList)
//    }
    }}
