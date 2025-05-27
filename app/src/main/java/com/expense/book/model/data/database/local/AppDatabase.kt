// AppDatabase.kt
package com.expense.book.model.data.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.expense.book.model.data.database.local.dao.AccountDao
import com.expense.book.model.data.database.local.dao.CategoryDao
import com.expense.book.model.data.database.local.dao.ExpenseDao
import com.expense.book.model.data.database.local.dao.IncomeDao
import com.expense.book.model.data.database.local.entities.Account
import com.expense.book.model.data.database.local.entities.Category
import com.expense.book.model.data.database.local.entities.Expense
import com.expense.book.model.data.database.local.entities.Income

@Database(
    entities = [Account::class,Category::class, Income::class, Expense::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
}