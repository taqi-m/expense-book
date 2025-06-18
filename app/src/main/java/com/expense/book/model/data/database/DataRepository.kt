package com.expense.book.model.data.database

import android.util.Log
import com.expense.book.model.data.AppPreferences
import com.expense.book.model.data.database.local.dao.AccountDao
import com.expense.book.model.data.database.local.dao.CategoryDao
import com.expense.book.model.data.database.local.dao.ExpenseDao
import com.expense.book.model.data.database.local.dao.IncomeDao
import com.expense.book.model.data.database.local.entities.Account
import com.expense.book.model.data.database.local.entities.Category
import com.expense.book.model.data.database.local.entities.Expense
import com.expense.book.model.data.database.local.entities.Income
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val accountDao: AccountDao,
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
            Category(categoryName = "Groceries", categoryType = "Expense"),
            Category(categoryName = "Rent", categoryType = "Expense"),
            Category(categoryName = "Utilities", categoryType = "Expense"),
            Category(categoryName = "Transportation", categoryType = "Expense"),
            Category(categoryName = "Food", categoryType = "Expense"),
            Category(categoryName = "Entertainment", categoryType = "Expense"),
            Category(categoryName = "Shopping", categoryType = "Expense"),
            Category(categoryName = "Healthcare", categoryType = "Expense"),
            Category(categoryName = "Education", categoryType = "Expense"),

            Category(categoryName = "Salary", categoryType = "Income"),
            Category(categoryName = "Business", categoryType = "Income"),
            Category(categoryName = "Investment", categoryType = "Income"),
            Category(categoryName = "Freelance", categoryType = "Income"),
            Category(categoryName = "Gift", categoryType = "Income"),
            Category(categoryName = "Other", categoryType = "Income")


        )

        val defaultAccounts = listOf(
            Account(accountName = "Cash", description = "Cash Amount"),
            Account(accountName = "Card", description = "Credit Card"),
            Account(accountName = "Savings", description = "SavingS Fund"),
        )

        try {
            for (category in defaultCategories) {
                val existingType = categoryDao.getCategoryByNameAndType(
                    category.categoryName,
                    category.categoryType
                )
                if (existingType == null) {
                    categoryDao.insert(category)
                }
            }

            for (account in defaultAccounts) {
                val existingAccount = accountDao.getAccountByName(account.accountName)
                if (existingAccount == null) {
                    accountDao.insert(account)
                }
            }
        } catch (e: Exception) {
            Log.e("DataRepository", "Error adding default types: ${e.message}")
        } finally {
            // Mark default types as added
            appPreferences.setDefaultTypesAdded()
            Log.d("DataRepository", "Default types added")
        }


    }

    // Type Operations
    suspend fun insertType(category: Category) {
        categoryDao.insert(category)
    }

    fun getAllTypes(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    // Income Operations
    suspend fun insertIncome(income: Income) {
        incomeDao.insert(income)
    }

    fun getAllIncome(): Flow<List<Income>> {
        return incomeDao.getAllIncome()
    }

    fun getIncomeByAccount(accountId: Long): Flow<List<Income>> {
        return incomeDao.getIncomeByAccount(accountId)
    }

    // Expense Operations
    suspend fun insertExpense(expense: Expense) {
        expenseDao.insert(expense)
    }

    fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    fun getExpensesByType(typeId: Long): Flow<List<Expense>> {
        return expenseDao.getExpensesByCategory(typeId)
    }

    fun getAllAccounts(): Flow<List<Account>> {
        return accountDao.getAllAccounts()
    }
}