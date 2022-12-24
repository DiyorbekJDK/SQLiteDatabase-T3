package com.diyorbek.sqlitedatabaset3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.diyorbek.sqlitedatabaset3.R
import com.diyorbek.sqlitedatabaset3.database.FoodDatabase
import com.diyorbek.sqlitedatabaset3.databinding.FragmentAddFoodBinding
import com.diyorbek.sqlitedatabaset3.model.Food
import com.google.android.material.snackbar.Snackbar

class AddFoodFragment : Fragment() {
    private var _binding: FragmentAddFoodBinding? = null
    private val binding get() = _binding!!
    private var food: Food? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        food = arguments?.getParcelable("food") as? Food
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = FoodDatabase(requireContext())
        if (food != null) {
            binding.btnSaveFood.text = "Save changes"
            binding.apply {
                editName.setText(food?.name)
                editIngredients.setText(food?.ingredients)
                editRecipe.setText(food?.recipe)
                textTitle.text = "Change Food"
            }
            binding.btnSaveFood.setOnClickListener {
                database.updateFood(
                    Food(
                        food?.id ?: 0,
                        binding.editName.text.toString().trim(),
                        binding.editIngredients.text.toString().trim(),
                        binding.editRecipe.text.toString().trim()
                    )
                )

                Snackbar.make(this.requireView(), "Updated", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
                findNavController().popBackStack()
            }

        } else {
            binding.btnSaveFood.setOnClickListener {
                database.saveFood(
                    Food(
                        food?.id ?: 0,
                        binding.editName.text.toString().trim(),
                        binding.editIngredients.text.toString().trim(),
                        binding.editRecipe.text.toString().trim()
                    )
                )

                Snackbar.make(this.requireView(), "Saved", Snackbar.LENGTH_SHORT).show()
                binding.editName.text?.clear()
                binding.editIngredients.text?.clear()
                binding.editRecipe.text?.clear()

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}