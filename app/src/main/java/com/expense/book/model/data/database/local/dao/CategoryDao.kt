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

    @Query("SELECT * FROM category WHERE categoryName = :category AND type = :typeName LIMIT 1")
    suspend fun getCategoryByNameAndType(typeName: String, category: String): Category?

    @Query("SELECT * FROM category WHERE type = :type")
    fun getCategoriesByType(type: String): Flow<List<Category>>

    @Query("SELECT * FROM category")
    fun getAllTypes(): Flow<List<Category>>
}