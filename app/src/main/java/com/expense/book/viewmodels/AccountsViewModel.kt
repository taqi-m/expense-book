package com.expense.book.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expense.book.model.data.database.DataRepository
import com.expense.book.model.data.database.local.entities.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {
    @Suppress("PropertyName")
    val _allAccounts = MutableStateFlow<List<Account>>(emptyList())
    val allAccounts: StateFlow<List<Account>> = _allAccounts

    init {
        viewModelScope.launch {
            loadAccounts()
        }
    }

    private  fun loadAccounts() {
        viewModelScope.launch {
            repository.getAllAccounts().collect { accounts ->
                _allAccounts.value = accounts
            }
        }
    }
}