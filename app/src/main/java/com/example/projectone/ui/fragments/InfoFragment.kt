package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindls.R
import com.example.kotlindls.databinding.FragmentInfoBinding
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.data.viewModel.CryptoViewModel
import com.example.projectone.data.viewModel.InflationViewModel
import com.example.projectone.data.viewModel.SelicViewModel
import com.example.projectone.data.viewModel.TickerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class InfoFragment : Fragment() {

    private var _binding : FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selicViewModel: SelicViewModel
    private lateinit var inflationViewModel: InflationViewModel
    private lateinit var tickerViewModel: TickerViewModel
    private lateinit var cryptoViewModel: CryptoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        getSelicData()
        getInflationData()
        getTickerData()
        getCryptoData()

        val formatData = SimpleDateFormat("dd/MM/yyyy")
        val calendar = Calendar.getInstance()
        val dateNow = formatData.format(calendar.time)
        val lastUpdate = view.findViewById<TextView>(R.id.textViewTitle)
        lastUpdate.text = "Última atualização $dateNow"
    }

    private fun initViewModel(){
        selicViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SelicViewModel::class.java]
        inflationViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[InflationViewModel::class.java]
        tickerViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[TickerViewModel::class.java]
        cryptoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CryptoViewModel::class.java]
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
                        selicViewModel.getSelicDB()
                            .observe(viewLifecycleOwner){ selicList ->
                                if (selicList != null) {
                                    binding.tvSelic.text = ("Selic: ${selicList.value}%")
                                }
                            }
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
                        inflationViewModel.getInflationDB()
                            .observe(viewLifecycleOwner){ inflationList ->
                                if (inflationList != null) {
                                    binding.tvInflation.text = ("Inflação: ${inflationList.value}%")
                                }
                            }
                    }
                    is ApiResult.Error -> {
                        Timber.d("Error loading inflation data: ${apiResult.exception}")
                    }
                }
            }
        }

    }

    private fun getTickerData() {
        CoroutineScope(Dispatchers.Main).launch {
            tickerViewModel.getTickerData().collect { apiResult ->
                when (apiResult) {
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

    private fun getCryptoData() {
        CoroutineScope(Dispatchers.Main).launch {
            cryptoViewModel.getCryptoData().collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Loading -> {
                        Timber.d("Loading Crypto data")
                    }
                    is ApiResult.Success -> {
                        Timber.d("Successful Crypto data")

                    }
                    is ApiResult.Error -> {
                        Timber.d("Error loading Crypto data: ${apiResult.exception}")
                    }
                }
            }
        }
    }
}