package com.example.projectone.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.viewModel.InflationViewModel
import com.example.projectone.data.viewModel.SelicViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentHome : BaseFragment<FragmentHomeBinding>(){

    private val selicViewModel = SelicViewModel()
    private val inflationViewModel = InflationViewModel()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            selicViewModel.getSelic()
            inflationViewModel.getInflation()
        }

        selicViewModel.selicData.observe(viewLifecycleOwner, Observer { selicRate ->
            selicRate.primeRate.forEach { selicModel ->
                binding.tvSelic.text = ("Selic : ${selicModel.value}%")
            }

            inflationViewModel.inflationData.observe(viewLifecycleOwner, Observer{inflation ->
                inflation.inflation.forEach{inflationRate ->
                    binding.tvInflation.text = ("Infação: ${inflationRate.value}%")

                }
            })

        })


    }

}