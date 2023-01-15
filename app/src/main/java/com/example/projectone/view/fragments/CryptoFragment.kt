package com.example.projectone.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindls.databinding.CryptoFragmentBinding
import com.example.projectone.base.BaseFragment

class CryptoFragment : BaseFragment<CryptoFragmentBinding>(){

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): CryptoFragmentBinding {
        return CryptoFragmentBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}