package com.example.projectone.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.kotlindls.R
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.kotlindls.databinding.FragmentTickerDetailBinding
import com.example.projectone.data.viewModel.CryptoViewModel
import com.example.projectone.data.viewModel.TickerViewModel
import kotlinx.coroutines.launch


class TickerDetailFragment : Fragment() {

    private var _binding : FragmentTickerDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var cryptoViewModel: CryptoViewModel

    private val args: TickerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTickerDetailBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cryptoViewModel = ViewModelProvider(this)[CryptoViewModel::class.java]
        getArgs()
    }
    private fun getArgs(){

        lifecycleScope.launch {
            val ticker = cryptoViewModel.getTickerByName(args.detail.coins)

            cryptoViewModel.getCryptoData(ticker.toString(), "BRL")
            cryptoViewModel.cryptoData.observe(viewLifecycleOwner){ cryptoDataResponse ->
                cryptoDataResponse.coins.forEach { coin ->
                    binding.apply {
                        tvCurrency.text = coin.currency
                        tvCurrencyRateFromUSD.text = coin.currencyRateFromUSD.toString()
                        tvCoinName.text = coin.coinName
                        tvCoin.text = coin.coin
                        tvRegularMarketChange.text = coin.regularMarketChange.toString()
                        tvRegularMarketPrice.text = coin.regularMarketPrice.toString()
                        tvRegularMarketChangePercent.text = coin.regularMarketChangePercent.toString()
                        tvRegularMarketDayLow.text = coin.regularMarketDayLow.toString()
                        tvRegularMarketDayHigh.text = coin.regularMarketDayHigh.toString()
                        tvRegularMarketDayRange.text = coin.regularMarketDayRange
                        tvRegularMarketVolume.text = coin.regularMarketVolume.toString()
                        tvMarketCap.text = coin.marketCap.toString()
                        tvRegularMarketTime.text = coin.regularMarketTime.toString()
                    }
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}