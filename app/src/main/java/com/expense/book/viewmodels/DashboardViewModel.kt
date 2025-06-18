package com.expense.book.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expense.book.model.data.database.DataRepository
import com.expense.book.model.data.database.local.entities.Category
import com.expense.book.model.data.database.local.entities.Expense
import com.expense.book.model.data.database.local.entities.Income
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    // StateFlow for recent income transactions
    private val _recentIncome = MutableStateFlow<List<Income>>(emptyList())
    val recentIncome: StateFlow<List<Income>> = _recentIncome

    // StateFlow for recent expense transactions
    private val _recentExpenses = MutableStateFlow<List<Expense>>(emptyList())
    val recentExpenses: StateFlow<List<Expense>> = _recentExpenses

    // StateFlow for all types (for filtering or display purposes)
    private val _allTypes = MutableStateFlow<List<Category>>(emptyList())
    val allTypes: StateFlow<List<Category>> = _allTypes

    // StateFlow for total income
    private val _totalIncome = MutableStateFlow(0.0)
    val totalIncome: StateFlow<Double> = _totalIncome

    // StateFlow for total expense
    private val _totalExpense = MutableStateFlow(0.0)
    val totalExpense: StateFlow<Double> = _totalExpense

    init {
        viewModelScope.launch {
            repository.addDefaultTypesIfNotExist()
        }
        // Load initial data from the database
        loadRecentIncome()
        loadRecentExpenses()
        loadAllTypes()
        calculateTotalIncome()
        calculateTotalExpense()
    }

    // Load recent income transactions
    private fun loadRecentIncome() {
        viewModelScope.launch {
            // Fetch income data from the repository
            repository.getAllIncome().collect { incomeList ->
                _recentIncome.value = incomeList
            }
        }
    }

    // Load recent expense transactions
    private fun loadRecentExpenses() {
        viewModelScope.launch {
            // Fetch expense data from the repository
            repository.getAllExpenses().collect { expenseList ->
                _recentExpenses.value = expenseList
            }
        }
    }

    // Load all types (for filtering or display purposes)
    private fun loadAllTypes() {
        viewModelScope.launch {
            // Fetch all types from the repository
            repository.getAllTypes().collect { types ->
                _allTypes.value = types
            }
        }
    }

    // Calculate total income
    private fun calculateTotalIncome() {
        viewModelScope.launch {
            repository.getAllIncome().collect { incomeList ->
                _totalIncome.value = incomeList.sumOf { it.amount }
            }
        }
    }

    // Calculate total expense
    private fun calculateTotalExpense() {
        viewModelScope.launch {
            repository.getAllExpenses().collect { expenseList ->
                _totalExpense.value = expenseList.sumOf { it.amount }
            }
        }
    }

    // Insert a new income record
    fun insertIncome(income: Income) {
        viewModelScope.launch {
            repository.insertIncome(income)
            // Refresh the recent income list
            loadRecentIncome()
            calculateTotalIncome()
        }
    }

    // Insert a new expense record
    fun insertExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insertExpense(expense)
            // Refresh the recent expense list
            loadRecentExpenses()
            calculateTotalExpense()
        }
    }

    // Insert a new type
    fun insertType(category: Category) {
        viewModelScope.launch {
            repository.insertType(category)
            // Refresh the types list
            loadAllTypes()
        }
    }
}