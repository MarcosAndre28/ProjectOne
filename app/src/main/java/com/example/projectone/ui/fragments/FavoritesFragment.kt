package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindls.databinding.FavoritesFragmentBinding
import com.example.projectone.base.BaseFragment
import com.example.projectone.data.viewModel.FavoriteTickerViewModel
import com.example.projectone.ui.adapter.FavoriteTickerAdapter

class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>() {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FavoritesFragmentBinding {
        return FavoritesFragmentBinding.inflate(inflater, container, false)
    }

    private lateinit var favoriteTickerViewModel: FavoriteTickerViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteTickerViewModel = ViewModelProvider(this)[FavoriteTickerViewModel::class.java]
        favoriteTickerViewModel.getAllFavoriteTickers().observe(viewLifecycleOwner){favoriteTicker ->
            binding.apply {
                recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewFavorites.setHasFixedSize(true)

                val adapter = FavoriteTickerAdapter(favoriteTicker)
                recyclerViewFavorites.adapter = adapter
            }
        }

    }
}