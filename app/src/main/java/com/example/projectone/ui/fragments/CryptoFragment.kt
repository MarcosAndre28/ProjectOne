package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindls.databinding.CryptoFragmentBinding
import com.example.projectone.adapter.CryptoAdapter
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.viewModel.CryptoViewModel

class CryptoFragment : BaseFragment<CryptoFragmentBinding>() {

    lateinit var cryptoViewModel: CryptoViewModel

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CryptoFragmentBinding {
        return CryptoFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cryptoViewModel = ViewModelProvider(this)[CryptoViewModel::class.java]
        cryptoViewModel.getAllCryptos().observe(viewLifecycleOwner) { crypto ->
            binding.apply {
                recyclerViewCrypto.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewCrypto.setHasFixedSize(true)

                val adapter = CryptoAdapter(crypto)
                recyclerViewCrypto.adapter = adapter
            }
        }
    }
}