package com.expense.book.model.data.database

import android.util.Log
import com.expense.book.model.data.AppPreferences
import com.expense.book.model.data.database.local.dao.AccountDao
import com.expense.book.model.data.database.local.dao.ExpenseCategoryDao
import com.expense.book.model.data.database.local.dao.ExpenseDao
import com.expense.book.model.data.database.local.dao.IncomeDao
import com.expense.book.model.data.database.local.entities.Account
import com.expense.book.model.data.database.local.entities.Expense
import com.expense.book.model.data.database.local.entities.ExpenseCategory
import com.expense.book.model.data.database.local.entities.Income
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val accountDao: AccountDao,
    private val expenseCategoryDao: ExpenseCategoryDao,
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

        val defaultExpenseCategories = listOf(
            ExpenseCategory(categoryName = "Groceries"),
            ExpenseCategory(categoryName = "Rent"),
            ExpenseCategory(categoryName = "Utilities"),
            ExpenseCategory(categoryName = "Transportation"),
            ExpenseCategory(categoryName = "Food"),
            ExpenseCategory(categoryName = "Entertainment"),
            ExpenseCategory(categoryName = "Shopping"),
            ExpenseCategory(categoryName = "Healthcare"),
            ExpenseCategory(categoryName = "Education")

        )

        val defaultAccounts = listOf(
            Account(accountName = "Cash", description = "Cash Amount"),
            Account(accountName = "Card", description = "Credit Card"),
            Account(accountName = "Savings", description = "SavingS Fund"),
        )

        try {
            for (category in defaultExpenseCategories) {
                val existingType = expenseCategoryDao.getCategoryByName(category.categoryName)
                if (existingType == null) {
                    expenseCategoryDao.insert(category)
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
        }


    }

    // Type Operations
    suspend fun insertType(expenseCategory: ExpenseCategory) {
        expenseCategoryDao.insert(expenseCategory)
    }

    fun getAllTypes(): Flow<List<ExpenseCategory>> {
        return expenseCategoryDao.getAllTypes()
    }

    // Income Operations
    suspend fun insertIncome(income: Income) {
        incomeDao.insert(income)
    }

    suspend fun getAllIncome(): Flow<List<Income>> {
        return incomeDao.getAllIncome()
    }

    suspend fun getIncomeByAccount(accountId: Long): Flow<List<Income>> {
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

    suspend fun getAllAccounts(): Flow<List<Account>> {
        return accountDao.getAllAccounts()
    }
}