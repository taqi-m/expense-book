package com.expense.book.view.navigation.screen.dashboard.navScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.expense.book.view.componenets.ButtonNewCategory
import com.expense.book.view.componenets.CategoryItem
import com.expense.book.view.componenets.TextToggleSwitch
import com.expense.book.viewmodels.CategoriesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val selectedType by viewModel.selectedEntryType.collectAsState()
    val currentCategories by viewModel.currentCategories.collectAsState()


    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TextToggleSwitch(
                modifier = Modifier.padding(bottom = 8.dp),
                firstOption = "Expense",
                secondOption = "Income",
                currentSelection = selectedType,
                onToggle = { entryType ->
                    viewModel.onEntryTypeSelected(entryType)
                }
            )
        }

        items(
            items = currentCategories,
            key = { it.categoryName }
        ) { category ->
            CategoryItem(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
//                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(4.dp)
                    ),
                categoryName = category.categoryName
            ) {

            }
        }

        item {
            ButtonNewCategory(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    ),
                onClick = {
//                    viewModel.onAddNewCategoryClicked()
                }
            )
        }
    }
}

