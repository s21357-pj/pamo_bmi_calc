package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(
            HomeViewModel::class.java
        )
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root ?: throw IllegalStateException("Root view not available")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}