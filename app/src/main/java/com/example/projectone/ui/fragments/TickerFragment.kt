package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindls.databinding.TickerFragmentBinding
import com.example.projectone.adapter.TickerAdapter
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.viewModel.TickerViewModel

class TickerFragment : BaseFragment<TickerFragmentBinding>(){

    lateinit var tickerViewModel: TickerViewModel

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): TickerFragmentBinding {
        return TickerFragmentBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tickerViewModel = ViewModelProvider(this)[TickerViewModel::class.java]
        tickerViewModel.getAllTickers().observe(viewLifecycleOwner){ticker ->
            binding.apply {
                recyclerViewTicker.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewTicker.setHasFixedSize(true)

                val adapter = TickerAdapter(ticker)
                recyclerViewTicker.adapter = adapter
            }
        }
    }
}