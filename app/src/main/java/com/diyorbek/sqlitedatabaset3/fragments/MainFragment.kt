package com.diyorbek.sqlitedatabaset3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.diyorbek.sqlitedatabaset3.R
import com.diyorbek.sqlitedatabaset3.databinding.FragmentMainBinding
import com.google.android.material.button.MaterialButton

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_foodMenuFragment)
        }
        binding.btnAddFood.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFoodFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}