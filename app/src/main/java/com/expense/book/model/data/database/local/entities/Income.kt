package com.expense.book.model.data.database.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income")
data class Income(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val categoryId: Long, // Foreign key to Type table
    val buyerName: String,
    val quantity: Double,
    val price: Double,
    val date: Long // Timestamp for the transaction
)