package com.expense.book.model.data.database.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val categoryName: String, // e.g., "Rora", "Stone Purchase", etc.
    val type: String, // "Income" or "Expense"
    val description: String? = null // Optional description
)