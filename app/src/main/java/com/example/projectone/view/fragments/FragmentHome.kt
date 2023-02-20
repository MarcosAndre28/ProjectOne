package com.example.projectone.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.data.viewModel.InflationViewModel
import com.example.projectone.data.viewModel.SelicViewModel
import com.example.projectone.data.viewModel.TickerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class FragmentHome : BaseFragment<FragmentHomeBinding>() {

    lateinit var selicViewModel: SelicViewModel
    lateinit var inflationViewModel: InflationViewModel
    lateinit var tickerViewModel: TickerViewModel

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selicViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(SelicViewModel::class.java)
        inflationViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(InflationViewModel::class.java)
        tickerViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(TickerViewModel::class.java)

        getSelicData()
        getInflationData()
        getTickerData()

    }
    private fun getSelicData() {
        CoroutineScope(Dispatchers.Main).launch {
            selicViewModel.getSelicData().collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Loading -> {
                        Timber.d("Loading Selic data")
                    }
                    is ApiResult.Success -> {
                        Timber.d("Successful Selic data")
                        selicViewModel.getSelicDB().observe(viewLifecycleOwner, Observer { selicList ->
                            if (selicList != null){
                                binding.tvSelic.text = ("Selic: ${selicList.value}%")
                            }
                        })
                    }
                    is ApiResult.Error -> {
                        Timber.d("Error loading Selic data: ${apiResult.exception}")
                    }
                }
            }
        }
    }

    private fun getInflationData() {
        CoroutineScope(Dispatchers.Main).launch {
            inflationViewModel.getInflation().collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Loading -> {
                        Timber.d("Loading inflation data")
                    }
                    is ApiResult.Success -> {
                        Timber.d("Successful inflation data")
                        inflationViewModel.getInflationDB().observe(viewLifecycleOwner, Observer { inflationList ->
                            if (inflationList != null){
                                binding.tvInflation.text = ("Inflação: ${inflationList.value}%")
                            }
                        })
                    }
                    is ApiResult.Error -> {
                        Timber.d("Error loading inflation data: ${apiResult.exception}")
                    }
                }
            }
        }

    }

    private  fun getTickerData(){
        CoroutineScope(Dispatchers.Main).launch {
            tickerViewModel.getTickerData().collect{apiResult ->
                when (apiResult){
                    is ApiResult.Loading -> {
                        Timber.d("Loading Ticker data")
                    }
                    is ApiResult.Success -> {
                        Timber.d("Successful Ticker data")

                    }
                    is ApiResult.Error -> {
                        Timber.d("Error loading Ticker data: ${apiResult.exception}")
                    }
                }
            }
        }
    }
}

