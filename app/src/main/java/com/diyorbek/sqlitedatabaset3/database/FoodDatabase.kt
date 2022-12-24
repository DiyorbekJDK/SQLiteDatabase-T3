package com.diyorbek.sqlitedatabaset3.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.diyorbek.sqlitedatabaset3.model.Food
import com.diyorbek.sqlitedatabaset3.util.Constantas.DB_NAME
import com.diyorbek.sqlitedatabaset3.util.Constantas.ID
import com.diyorbek.sqlitedatabaset3.util.Constantas.INGREDIENTS
import com.diyorbek.sqlitedatabaset3.util.Constantas.NAME
import com.diyorbek.sqlitedatabaset3.util.Constantas.RECIPE
import com.diyorbek.sqlitedatabaset3.util.Constantas.TABLE_NAME

class FoodDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1), FoodService {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME" +
                "($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL, $INGREDIENTS TEXT NOT NULL, $RECIPE TEXT NOT NULL)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    override fun saveFood(food: Food) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, food.name)
        contentValues.put(INGREDIENTS, food.ingredients)
        contentValues.put(RECIPE, food.recipe)
        database.insert(TABLE_NAME,null,contentValues)
        database.close()
    }

    override fun updateFood(food: Food) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, food.name)
        contentValues.put(INGREDIENTS, food.ingredients)
        contentValues.put(RECIPE, food.recipe)
        database.update(TABLE_NAME,contentValues,"$ID = ?", arrayOf(food.id.toString())
        )
        database.close()
    }

    override fun deleteFood(id: Int) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$ID = ?", arrayOf(id.toString()))
    }

    override fun getFoodList(): List<Food> {
        val database = this.readableDatabase
        val list = mutableListOf<Food>()
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    Food(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3))

                )
            }while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return list
    }
}