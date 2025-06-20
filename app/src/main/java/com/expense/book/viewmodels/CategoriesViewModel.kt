package com.expense.book.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expense.book.model.data.ENTRY_TYPE
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

    private val _allCategories = MutableStateFlow<List<Category>>(emptyList())

    private val _incomeCategories = MutableStateFlow<List<Category>>(emptyList())

    private val _expenseCategories = MutableStateFlow<List<Category>>(emptyList())

    private val _currentCategories = MutableStateFlow<List<Category>>(emptyList())
    val currentCategories: StateFlow<List<Category>> = _currentCategories

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> = _selectedCategory


    private val _selectedEntryType = MutableStateFlow(ENTRY_TYPE.EXPENSE)
    val selectedEntryType: StateFlow<ENTRY_TYPE> = _selectedEntryType

    val isNewCategoryDialogVisible = MutableStateFlow(false)
    val isEditCategoryDialogVisible = MutableStateFlow(false)

    init {
/*        viewModelScope.launch {
            repository.addDefaultTypesIfNotExist()
        }*/
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
                updateSelectedEntryType(ENTRY_TYPE.EXPENSE)
                Log.d("DataRepository", "All types: $types")
            }
        }
    }

    // Update selected entry type
    private fun updateSelectedEntryType(entryType: ENTRY_TYPE) {
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

    fun onEntryTypeSelected(entryType: ENTRY_TYPE) {
        if (_selectedEntryType.value != entryType) {
            updateSelectedEntryType(entryType)
        }
    }

    fun onAddNewCategoryClicked() {
        isNewCategoryDialogVisible.value = !isNewCategoryDialogVisible.value
    }

    fun addNewCategory(name: String, description: String) {
//        TODO: Add category to database
    }

    fun onEditCategoryClicked(category: Category?) {
        _selectedCategory.value = category
        isEditCategoryDialogVisible.value = !isEditCategoryDialogVisible.value
    }

    fun updateCategory(category: Category){

    }
}