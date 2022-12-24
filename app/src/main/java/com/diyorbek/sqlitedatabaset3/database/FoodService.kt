package com.diyorbek.sqlitedatabaset3.database

import com.diyorbek.sqlitedatabaset3.model.Food

interface FoodService {
    fun saveFood(food: Food)
    fun updateFood(food: Food)
    fun deleteFood(id: Int)
    fun getFoodList(): List<Food>
}