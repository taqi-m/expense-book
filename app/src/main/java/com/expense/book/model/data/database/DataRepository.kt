package com.expense.book.model.data.database

import android.util.Log
import com.expense.book.model.data.AppPreferences
import com.expense.book.model.data.database.local.dao.CategoryDao
import com.expense.book.model.data.database.local.dao.ExpenseDao
import com.expense.book.model.data.database.local.dao.IncomeDao
import com.expense.book.model.data.database.local.entities.Category
import com.expense.book.model.data.database.local.entities.Expense
import com.expense.book.model.data.database.local.entities.Income
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val incomeDao: IncomeDao,
    private val expenseDao: ExpenseDao,
    private val appPreferences: AppPreferences
) {
    suspend fun addDefaultTypesIfNotExist() {
        // Check if default types have already been added
        if (appPreferences.areDefaultTypesAdded()) {
            Log.d("DataRepository", "Default types already added")
            return
        }

       val defaultCategories = listOf(
            Category(categoryName = "Salary", type = "Income"),
            Category(categoryName = "Freelance", type = "Income"),
            Category(categoryName = "Investments", type = "Income"),
            Category(categoryName = "Gifts", type = "Income"),

            Category(categoryName = "Groceries", type = "Expense"),
            Category(categoryName = "Rent", type = "Expense"),
            Category(categoryName = "Utilities", type = "Expense"),
            Category(categoryName = "Transportation", type = "Expense"),
            Category(categoryName = "Food", type = "Expense"),
            Category(categoryName = "Entertainment", type = "Expense"),
            Category(categoryName = "Shopping", type = "Expense"),
            Category(categoryName = "Healthcare", type = "Expense"),
            Category(categoryName = "Education", type = "Expense")

        )

//        val defaultCategories = listOf(
//            Category(typeName = "Income", type = "Income"),
//            Category(typeName = "Expense", type = "Expense")
//        )






        for (category in defaultCategories) {
            val existingType = categoryDao.getCategoryByNameAndType(category.categoryName, category.type)
            if (existingType == null) {
                categoryDao.insert(category)
            }
        }

        // Mark default types as added
        appPreferences.setDefaultTypesAdded()
    }

    // Type Operations
    suspend fun insertType(category: Category) {
        categoryDao.insert(category)
    }

    fun getTypesByCategory(category: String): Flow<List<Category>> {
        return categoryDao.getCategoriesByType(category)
    }

    fun getAllTypes(): Flow<List<Category>> {
        return categoryDao.getAllTypes()
    }

    // Income Operations
    suspend fun insertIncome(income: Income) {
        incomeDao.insert(income)
    }

    fun getAllIncome(): Flow<List<Income>> {
        return incomeDao.getAllIncome()
    }

    fun getIncomeByType(typeId: Long): Flow<List<Income>> {
        return incomeDao.getIncomeByType(typeId)
    }

    // Expense Operations
    suspend fun insertExpense(expense: Expense) {
        expenseDao.insert(expense)
    }

    fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    fun getExpensesByType(typeId: Long): Flow<List<Expense>> {
        return expenseDao.getExpensesByType(typeId)
    }
}