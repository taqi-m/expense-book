package com.expense.book.model.data.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.expense.book.model.data.database.local.entities.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseCategoryDao {
    @Insert
    suspend fun insert(expenseCategory: ExpenseCategory)

    @Query("SELECT * FROM category WHERE categoryName = :categoryName LIMIT 1")
    suspend fun getCategoryByName(categoryName: String): ExpenseCategory?

    @Query("SELECT * FROM category")
    fun getAllTypes(): Flow<List<ExpenseCategory>>
}