package com.expense.book.view.navigation.screen.dashboard.navScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.expense.book.view.componenets.CategoryItem
import com.expense.book.view.componenets.TextToggleSwitch
import com.expense.book.viewmodels.CategoriesViewModel
import com.expense.book.viewmodels.CategoriesViewModel.ENTRY_TYPE

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val selectedType by viewModel.selectedEntryType.collectAsState()
    val currentCategories by viewModel.currentCategories.collectAsState()


    LazyColumn (
        modifier = Modifier
            .padding(horizontal =  16.dp),
    ){
        item {
            TextToggleSwitch(
                modifier = Modifier.padding(vertical = 8.dp),
                firstOption = "Income",
                secondOption = "Expense",
                isFirstSelected = selectedType == ENTRY_TYPE.INCOME,
                onToggle = { isFirstSelected ->
                    viewModel.updateSelectedEntryType(
                        if (isFirstSelected) ENTRY_TYPE.INCOME else ENTRY_TYPE.EXPENSE
                    )
                }
            )
        }

        items(
            items = currentCategories,
            key = { it.categoryName }
        ) { category ->
            CategoryItem(
                categoryName = category.categoryName
            ) {

            }
        }
    }
}

