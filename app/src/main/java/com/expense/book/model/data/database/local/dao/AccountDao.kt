package com.expense.book.model.data.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.expense.book.model.data.database.local.entities.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert
    suspend fun insert(account: Account)

    @Query("SELECT * FROM account ORDER BY accountName ASC")
    fun getAllAccounts(): Flow<List<Account>>


    @Query("SELECT * FROM account WHERE accountName = :accountName LIMIT 1")
    suspend fun getAccountByName(accountName: String): Account?
}