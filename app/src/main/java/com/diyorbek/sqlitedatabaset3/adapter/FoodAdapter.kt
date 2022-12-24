package com.diyorbek.sqlitedatabaset3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.sqlitedatabaset3.databinding.ItemLayoutBinding
import com.diyorbek.sqlitedatabaset3.model.Food

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    lateinit var onClick: (Food) -> Unit
    var foodList = mutableListOf<Food>()


    inner class FoodViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.foodName.text = food.name
            itemView.setOnClickListener {
                onClick.invoke(food)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(food = foodList[position])
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}