package com.expense.book.model.data.database.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income")
data class Income(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val accountId : Long, // Foreign key to Accounts table
    val amount: Double,
    val description: String? = null,
    val date: Long // Timestamp for the transaction
)