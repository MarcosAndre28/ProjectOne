package com.example.projectone.view.fragments

import android.os.Bundle
import android.view.View
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.projectone.base.BaseFragment

class FragmentHome : BaseFragment<FragmentHomeBinding>(){

    private lateinit var binding: FragmentHomeBinding

    override fun getViewBinding() =  FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = "Marcos"
    }
}