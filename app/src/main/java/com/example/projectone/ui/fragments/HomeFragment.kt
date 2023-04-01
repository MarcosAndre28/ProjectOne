package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import com.example.kotlindls.R
import com.example.kotlindls.databinding.FragmentHomeBinding
import com.example.projectone.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs(){
        val pageAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPage.adapter = pageAdapter

        pageAdapter.addFragment(InfoFragment(), R.string.title_fragment_info)
        pageAdapter.addFragment(CryptoFragment(),R.string.title_fragment_crypto)
        pageAdapter.addFragment(TickerFragment(),R.string.title_fragment_ticker)
        pageAdapter.addFragment(FavoritesFragment(),R.string.title_fragment_favorites)

        binding.viewPage.offscreenPageLimit = pageAdapter.itemCount
        TabLayoutMediator(binding.tabs, binding.viewPage){tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))

        }.attach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

