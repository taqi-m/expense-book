package com.expense.book.model.data.database.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val accountName: String,
    val description: String? = null,
    val currentBalance: Double = 0.0
)
