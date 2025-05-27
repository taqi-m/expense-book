package com.expense.book.model.data.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.expense.book.model.data.database.local.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM category WHERE categoryName = :categoryName LIMIT 1")
    suspend fun getCategoryByName(categoryName: String): Category?

    @Query("SELECT * FROM category WHERE categoryType = :categoryType AND categoryName = :categoryName LIMIT 1")
    suspend fun getCategoryByNameAndType(categoryName: String, categoryType: String): Category?

    @Query("SELECT * FROM category WHERE categoryType = 'expense'")
    fun getExpenseCategories(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE categoryType = 'income'")
    fun getIncomeCategories(): Flow<List<Category>>

    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<Category>>
}