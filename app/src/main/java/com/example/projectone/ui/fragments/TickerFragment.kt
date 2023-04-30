package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindls.databinding.TickerFragmentBinding
import com.example.projectone.data.viewModel.TickerViewModel
import com.example.projectone.db.model.TickerModelDB
import com.example.projectone.ui.adapter.TickerAdapter
import kotlinx.coroutines.launch

class TickerFragment : Fragment(){

    private var _binding : TickerFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TickerAdapter

    private lateinit var tickerViewModel: TickerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = TickerFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tickerViewModel = ViewModelProvider(this)[TickerViewModel::class.java]

        initRv()
    }

    private fun initRv(){
        tickerViewModel.allTickers.observe(viewLifecycleOwner) { ticker ->
            binding.apply {
                recyclerViewTicker.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewTicker.setHasFixedSize(true)

                if (!::adapter.isInitialized) {
                    adapter = TickerAdapter(ticker.toMutableList()) { tickerModel, isFavorite ->
                        onFavoriteClick(tickerModel, isFavorite)
                    }
                    recyclerViewTicker.adapter = adapter
                } else {
                    adapter.updateList(ticker.toMutableList())
                }
            }
        }
    }

    private fun onFavoriteClick(ticker: TickerModelDB, isFavorite: Boolean) {
        lifecycleScope.launch {
            tickerViewModel.updateFavoriteStatus(ticker.id, isFavorite)
            if (isFavorite){
                adapter.removeItem(ticker)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
