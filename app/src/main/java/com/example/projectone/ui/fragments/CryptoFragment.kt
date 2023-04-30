package com.example.projectone.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlindls.R
import com.example.kotlindls.databinding.CryptoFragmentBinding
import com.example.projectone.data.viewModel.CryptoViewModel
import com.example.projectone.db.model.CryptoAvailableModelDB
import com.example.projectone.ui.adapter.CryptoAdapter
import kotlinx.coroutines.launch
import java.util.Locale

class CryptoFragment : Fragment() {

    private lateinit var cryptoViewModel: CryptoViewModel
    private lateinit var cryptoAdapter: CryptoAdapter
    private var searchCryptoList: List<CryptoAvailableModelDB> = emptyList()
    private var isSearching = false

    private var _binding : CryptoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = CryptoFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoViewModel = ViewModelProvider(this)[CryptoViewModel::class.java]
        cryptoViewModel.getCryptoAvailable()

        initRv()
        initListeners()

    }

    private fun initRv(){
        cryptoAdapter = CryptoAdapter { item ->
            lifecycleScope.launch {
                val tickerModelDB = cryptoViewModel.getTickerByName(item.coins)
                val action = HomeFragmentDirections.actionNavigationHomeToTickerDetailFragment(tickerModelDB)
                findNavController().navigate(action)
            }
        }


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}