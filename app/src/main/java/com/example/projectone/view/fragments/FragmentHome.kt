package com.example.projectone.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.data.viewModel.InflationViewModel
import com.example.projectone.data.viewModel.SelicViewModel
import com.example.projectone.db.model.Selic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.reflect.Array.set

class FragmentHome : BaseFragment<FragmentHomeBinding>() {

    lateinit var selicViewModel: SelicViewModel
    private val inflationViewModel = InflationViewModel()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selicViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(SelicViewModel::class.java)

        selicViewModel.getSelicDB().observe(viewLifecycleOwner, Observer { selicList ->
            binding.tvSelic.text = ("Selic: ${selicList.value}%")
        })

    }

}