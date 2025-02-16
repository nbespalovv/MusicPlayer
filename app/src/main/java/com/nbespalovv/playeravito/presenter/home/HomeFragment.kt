package com.nbespalovv.playeravito.presenter.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nbespalovv.playeravito.R
import com.nbespalovv.playeravito.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = childFragmentManager.findFragmentById(R.id.fragmentContainerView)?.findNavController()
        navController?.let { NavigationUI.setupWithNavController(binding.bottomNavigationView, it) }
    }
}