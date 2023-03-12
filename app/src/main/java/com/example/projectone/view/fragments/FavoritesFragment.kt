package com.example.projectone.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindls.databinding.FavoritesFragmentBinding
import com.example.projectone.base.BaseFragment

class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FavoritesFragmentBinding {
        return FavoritesFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}