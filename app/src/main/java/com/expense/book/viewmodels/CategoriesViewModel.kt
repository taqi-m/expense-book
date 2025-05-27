package com.expense.book.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expense.book.model.data.database.DataRepository
import com.expense.book.model.data.database.local.entities.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    enum class ENTRY_TYPE {
        INCOME,
        EXPENSE
    }

    private val _allCategories = MutableStateFlow<List<Category>>(emptyList())

    private val _incomeCategories = MutableStateFlow<List<Category>>(emptyList())

    private val _expenseCategories = MutableStateFlow<List<Category>>(emptyList())

    private val _currentCategories = MutableStateFlow<List<Category>>(emptyList())
    val currentCategories: StateFlow<List<Category>> = _currentCategories

    private val _selectedEntryType = MutableStateFlow(ENTRY_TYPE.INCOME)
    val selectedEntryType: StateFlow<ENTRY_TYPE> = _selectedEntryType

    init {
        viewModelScope.launch {
            repository.addDefaultTypesIfNotExist()
        }
        // Load all types when the ViewModel is created
        loadAllCategories()
    }

    // Load all types from the repository
    private fun loadAllCategories() {
        viewModelScope.launch {
            repository.getAllTypes().collect { types ->
                _allCategories.value = types
                _incomeCategories.value = types.filter { it.categoryType == "Income" }
                _expenseCategories.value = types.filter { it.categoryType == "Expense" }
                _currentCategories.value = _incomeCategories.value // Default to income categories
                Log.d("DataRepository", "All types: $types")
            }
        }
    }

    // Update selected entry type
    fun updateSelectedEntryType(entryType: ENTRY_TYPE) {
        _selectedEntryType.value = entryType
        when (entryType) {
            ENTRY_TYPE.INCOME -> {
                _currentCategories.value = _incomeCategories.value
            }
            ENTRY_TYPE.EXPENSE -> {
                _currentCategories.value = _expenseCategories.value
            }
        }
    }
}