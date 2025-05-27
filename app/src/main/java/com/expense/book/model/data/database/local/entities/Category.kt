package com.expense.book.model.data.database.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val categoryName: String,
    val categoryType: String,
    val description: String? = null
)