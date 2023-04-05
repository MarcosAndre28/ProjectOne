package com.example.projectone.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.kotlindls.R
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.kotlindls.databinding.FragmentTickerDetailBinding


class TickerDetailFragment : Fragment() {

    private var _binding : FragmentTickerDetailBinding? = null
    private val binding get() = _binding!!

    private val args: TickerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTickerDetailBinding.inflate(inflater,container,false)
        return binding.root
        getArgs()
    }

    private fun getArgs(){
        args.detail.coins
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}