package com.diyorbek.sqlitedatabaset3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyorbek.sqlitedatabaset3.R
import com.diyorbek.sqlitedatabaset3.adapter.FoodAdapter
import com.diyorbek.sqlitedatabaset3.database.FoodDatabase
import com.diyorbek.sqlitedatabaset3.databinding.FragmentFoodMenuBinding

class FoodMenuFragment : Fragment() {
    private var _binding: FragmentFoodMenuBinding? = null
    private val binding get() = _binding!!
    private val foodAdapter by lazy { FoodAdapter() }
    private val foodDatabase by lazy { FoodDatabase(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = foodAdapter
        }
        foodAdapter.foodList = foodDatabase.getFoodList().toMutableList()
        foodAdapter.onClick = {
            val bundle = bundleOf("food" to it)
            findNavController().navigate(
                R.id.action_foodMenuFragment_to_detailFragment,bundle
            )
        }
    }

}