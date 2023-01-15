package com.example.projectone.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindls.databinding.TickerFragmentBinding
import com.example.projectone.base.BaseFragment

class TickerFragment : BaseFragment<TickerFragmentBinding>(){

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): TickerFragmentBinding {
       return TickerFragmentBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}