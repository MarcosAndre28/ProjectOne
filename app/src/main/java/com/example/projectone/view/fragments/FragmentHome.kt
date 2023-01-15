package com.example.projectone.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.projectone.base.BaseFragment

class FragmentHome : BaseFragment<FragmentHomeBinding>(){

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}