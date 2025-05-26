// AppDatabase.kt
package com.expense.book.model.data.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.expense.book.model.data.database.local.dao.AccountDao
import com.expense.book.model.data.database.local.dao.ExpenseCategoryDao
import com.expense.book.model.data.database.local.dao.ExpenseDao
import com.expense.book.model.data.database.local.dao.IncomeDao
import com.expense.book.model.data.database.local.entities.Account
import com.expense.book.model.data.database.local.entities.ExpenseCategory
import com.expense.book.model.data.database.local.entities.Expense
import com.expense.book.model.data.database.local.entities.Income

@Database(
    entities = [Account::class,ExpenseCategory::class, Income::class, Expense::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): ExpenseCategoryDao
    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
}