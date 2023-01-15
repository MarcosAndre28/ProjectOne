package com.example.projectone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.kotlindls.R
import com.example.kotlindls.databinding.ActivityMainBinding
import com.example.projectone.Inflation.InflationViewModel
import com.example.projectone.Inflation.InflationViewModelFactory
import com.example.projectone.Selic.SelicViewModel
import com.example.projectone.Selic.SelicViewModelFactory
import com.example.projectone.repositories.InflationRepository
import com.example.projectone.repositories.SelicRepository
import com.example.projectone.Api.RetrofitInstance

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var viewModelInflation: InflationViewModel
    private lateinit var viewModelSelic: SelicViewModel



    private val api = RetrofitInstance.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        setupWithNavController(bottomNavigationView, navController)

        viewModelInflation =
            ViewModelProvider(this, InflationViewModelFactory(InflationRepository(api))).get(
                InflationViewModel::class.java
            )

        viewModelSelic =
            ViewModelProvider(this, SelicViewModelFactory(SelicRepository(api))).get(
                SelicViewModel::class.java
            )


    }

    override fun onResume() {
        super.onResume()
        viewModelSelic.getSelic()
        viewModelInflation.getInflation()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }
}