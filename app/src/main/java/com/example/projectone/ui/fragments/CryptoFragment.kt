package com.example.projectone.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindls.R
import com.example.kotlindls.databinding.CryptoFragmentBinding
import com.example.projectone.adapter.CryptoAdapter
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.viewModel.CryptoViewModel
import com.example.projectone.db.model.CryptoAvailableModelDB
import kotlinx.coroutines.launch
import java.util.*

class CryptoFragment : BaseFragment<CryptoFragmentBinding>() {

    private lateinit var cryptoViewModel: CryptoViewModel
    private lateinit var cryptoAdapter: CryptoAdapter
    private var searchCryptoList: List<CryptoAvailableModelDB> = emptyList()
    private var isSearching = false

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CryptoFragmentBinding {
        return CryptoFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoViewModel = ViewModelProvider(this)[CryptoViewModel::class.java]

        cryptoViewModel.getCryptoAvailable()

        initRv();
        initListeners()

    }

    private fun initRv(){
        cryptoAdapter = CryptoAdapter()
        binding.recyclerViewCrypto.apply {
            layoutManager = GridLayoutManager(context,4)
            adapter = cryptoAdapter
        }

        lifecycleScope.launch {
            cryptoViewModel.getAllCryptoAvailable().observe(viewLifecycleOwner, Observer { cryptoList ->
                searchCryptoList = cryptoList
                cryptoAdapter.submitList(cryptoList)
            })
        }
    }

    private fun initListeners(){
        binding.search.setOnClickListener {
            if (isSearching){
                lifecycleScope.launch {
                    cryptoViewModel.getAllCryptoAvailable().observe(viewLifecycleOwner, Observer { cryptoList ->
                        cryptoAdapter.submitList(cryptoList)
                    })
                }
                binding.search.setImageResource(R.drawable.ic_search)
                isSearching = false
            } else {
                onSearchClick()
                binding.search.setImageResource(R.drawable.ic_close)
                isSearching = true
            }
        }
    }

    private fun onSearchClick(){
        val searchView = SearchView(requireContext())
        searchView.queryHint = "Pesquisar"
        searchView.onActionViewExpanded()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                searchCrypto(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
              searchCrypto(newText)
                return true
            }

        })

        val searchDialog = AlertDialog.Builder(requireContext())
            .setView(searchView)
            .create()

        searchDialog.show()
    }

    private fun searchCrypto(query: String){
        val lowercaseQuery = query.lowercase(Locale.ROOT)
        val filteredList = searchCryptoList.filter { crypto ->
            crypto.coins.lowercase(Locale.ROOT).contains(lowercaseQuery)
        }
        cryptoAdapter.submitList(filteredList)
    }
}