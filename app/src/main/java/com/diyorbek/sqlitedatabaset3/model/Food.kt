package com.diyorbek.sqlitedatabaset3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val id: Int? = null,
    val name: String,
    val ingredients: String,
    val recipe: String
): Parcelable
