package com.diyorbek.sqlitedatabaset3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.diyorbek.sqlitedatabaset3.R
import com.diyorbek.sqlitedatabaset3.database.FoodDatabase
import com.diyorbek.sqlitedatabaset3.databinding.FragmentDetailBinding
import com.diyorbek.sqlitedatabaset3.model.Food
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var food: Food? = null
    private val foodDatabase by lazy { FoodDatabase(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        food = arguments?.getParcelable("food") as? Food
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        food?.let { f ->
            binding.foodName.text = f.name
            binding.foodIngredient.text = f.ingredients
            binding.foodRecipe.text = f.recipe
            binding.btnDeleteFood.setOnClickListener {
                foodDatabase.deleteFood(f.id!!)
            Snackbar.make(this.requireView(),"Deleted!", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            binding.btnUpdateFood.setOnClickListener {
                val bundle = bundleOf("food" to f)
                findNavController().navigate(R.id.action_detailFragment_to_addFoodFragment, bundle)
            }
        }

    }
}