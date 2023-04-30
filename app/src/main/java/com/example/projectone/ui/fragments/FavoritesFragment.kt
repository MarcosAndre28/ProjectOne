package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlindls.databinding.FavoritesFragmentBinding

class FavoritesFragment : Fragment() {

    private var _binding : FavoritesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FavoritesFragmentBinding.inflate(inflater,container,false)
        return binding.root

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}