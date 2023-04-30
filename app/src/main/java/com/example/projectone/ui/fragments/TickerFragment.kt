package com.example.projectone.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kotlindls.R
import com.example.kotlindls.databinding.TickerFragmentBinding
import com.example.projectone.data.viewModel.TickerViewModel
import com.example.projectone.ui.adapter.TickerAdapter

class TickerFragment : Fragment(){

    private var _binding : TickerFragmentBinding? = null
    private val binding get() = _binding!!

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
        tickerViewModel.getAllTickers().observe(viewLifecycleOwner) { ticker ->
            binding.apply {
                recyclerViewTicker.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewTicker.setHasFixedSize(true)

                val adapter = TickerAdapter(ticker)
                recyclerViewTicker.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
