package com.expense.book.model.data.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.expense.book.model.data.database.local.entities.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Insert
    suspend fun insert(income: Income)

    @Query("SELECT * FROM income ORDER BY date DESC")
    fun getAllIncome(): Flow<List<Income>>

    @Query("SELECT * FROM income WHERE accountId = :accountId")
    fun getIncomeByAccount(accountId: Long): Flow<List<Income>>

}