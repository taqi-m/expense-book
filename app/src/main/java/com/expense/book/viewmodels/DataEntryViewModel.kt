package com.expense.book.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expense.book.model.data.ENTRY_TYPE
import com.expense.book.model.data.database.DataRepository
import com.expense.book.model.data.database.local.entities.Account
import com.expense.book.model.data.database.local.entities.Category
import com.expense.book.model.data.database.local.entities.Expense
import com.expense.book.model.data.database.local.entities.Income
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataEntryViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    // StateFlow for all types (for the TypeSpinner)
    private val _allTypes = MutableStateFlow<List<Category>>(emptyList())
    val allTypes: StateFlow<List<Category>> = _allTypes

    private val _allAccounts = MutableStateFlow<List<Account>>(emptyList())
    val allAccounts: StateFlow<List<Account>> = _allAccounts

    private val _currentAccount = MutableStateFlow<Account?>(null)
    val currentAccount: StateFlow<Account?> = _currentAccount

    // MutableState for selected type
    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> = _selectedCategory

    private val _selectedEntryType = MutableStateFlow(ENTRY_TYPE.INCOME)
    val selectedEntryType: StateFlow<ENTRY_TYPE> = _selectedEntryType

    // MutableState for input fields
    private val _buyerName = MutableStateFlow("")
    val buyerName: StateFlow<String> = _buyerName

    private val _quantity = MutableStateFlow("")
    val quantity: StateFlow<String> = _quantity

    private val _price = MutableStateFlow("")
    val price: StateFlow<String> = _price

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    init {
        viewModelScope.launch {
            repository.addDefaultTypesIfNotExist()
        }
        // Load all types when the ViewModel is created
        loadAllTypes()
    }

    // Load all types from the repository
    private fun loadAllTypes() {
        viewModelScope.launch {
            repository.getAllTypes().collect { types ->
                _allTypes.value = types
                _selectedCategory.value = _allTypes.value.firstOrNull()
                Log.d("DataRepository", "All types: $types")
            }
            repository.getAllAccounts().collect { accounts ->
                _allAccounts.value = accounts
                _currentAccount.value = _allAccounts.value.firstOrNull()
                Log.d("DataRepository", "All accounts: $accounts")
            }
        }
    }

    // Update selected type
    fun updateSelectedType(category: Category) {
        _selectedCategory.value = category
    }

    // Update selected entry type
    fun updateSelectedEntryType(entryType: ENTRY_TYPE) {
        _selectedEntryType.value = entryType
        Log.i("DataEntryViewModel", "Selected entry type: $entryType")
        // Clear inputs when switching types
        clearInputs()
    }

    // Update buyer name
    fun updateBuyerName(name: String) {
        _buyerName.value = name
    }

    // Update quantity
    fun updateQuantity(qty: String) {
        _quantity.value = qty
    }

    // Update price
    fun updatePrice(priceValue: String) {
        _price.value = priceValue
    }

    // Update amount
    fun updateAmount(amountValue: String) {
        _amount.value = amountValue
    }

    // Update description
    fun updateDescription(desc: String) {
        _description.value = desc
    }

    // Insert new income
    fun insertIncome() {
        viewModelScope.launch {
            val income = Income(
                accountId = _currentAccount.value?.id ?: 0,
                amount = _quantity.value.toDoubleOrNull() ?: 0.0,
                description = _description.value,
                date = System.currentTimeMillis()
            )
            repository.insertIncome(income)
            clearInputs()
        }
    }

    // Insert new expense
    fun insertExpense() {
        viewModelScope.launch {
            val expense = Expense(
                categoryId = _selectedCategory.value?.id ?: 0,
                accountId = _currentAccount.value?.id ?: 0,
                amount = _amount.value.toDoubleOrNull() ?: 0.0,
                description = _description.value,
                date = System.currentTimeMillis()
            )
            repository.insertExpense(expense)
            clearInputs()
        }
    }

    // Clear all input fields
    private fun clearInputs() {
        _buyerName.value = ""
        _quantity.value = ""
        _price.value = ""
        _amount.value = ""
        _description.value = ""
        _selectedCategory.value = null
    }

    fun onEntryTypeSelected(entryType: ENTRY_TYPE) {
        if (_selectedEntryType.value != entryType) {
            updateSelectedEntryType(entryType)
/*            when (entryType) {
                ENTRY_TYPE.INCOME -> {
                    _selectedCategory.value = _allTypes.value.firstOrNull { it.categoryType == "Income" }
                }
                ENTRY_TYPE.EXPENSE -> {
                    _selectedCategory.value = _allTypes.value.firstOrNull { it.categoryType == "Expense" }
                }
            }*/
        }
    }
}