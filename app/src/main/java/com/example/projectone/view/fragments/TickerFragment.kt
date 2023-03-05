package com.example.projectone.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.kotlindls.databinding.TickerFragmentBinding
import com.example.projectone.adapter.TickerAdapter
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.data.models.Stock
import com.example.projectone.data.models.TickerModel
import com.example.projectone.data.repositories.TickerRepository
import com.example.projectone.data.viewModel.TickerViewModel
import com.example.projectone.db.AppDatabase
import com.example.projectone.db.dao.TickerDao
import com.example.projectone.db.model.InflationModelDB
import com.example.projectone.db.model.TickerModelDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

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
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.setHasFixedSize(true)


                val adapter = TickerAdapter(ticker)
                recyclerView.adapter = adapter
            }

        }



    }

}