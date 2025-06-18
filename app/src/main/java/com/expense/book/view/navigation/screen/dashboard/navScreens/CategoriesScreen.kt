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
import com.expense.book.view.componenets.AddCategoryDialog
import com.expense.book.view.componenets.AddNewButton
import com.expense.book.view.componenets.CategoryItem
import com.expense.book.view.componenets.EditCategoryDialog
import com.expense.book.view.componenets.TextToggleSwitch
import com.expense.book.viewmodels.CategoriesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val selectedType by viewModel.selectedEntryType.collectAsState()
    val currentCategories by viewModel.currentCategories.collectAsState()
    val isNewCategoryDialogVisible by viewModel.isNewCategoryDialogVisible.collectAsState()
    val isEditCategoryDialogVisible by viewModel.isEditCategoryDialogVisible.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()


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
            key = { it.id }
        ) { category ->
            CategoryItem(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
//                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(4.dp)
                    ),
                categoryName = category.categoryName,
                onEditClick = {viewModel.onEditCategoryClicked(category)},
                onLongClick = {

                }
            )
        }

        item {
            AddNewButton(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    ),
                text = "Add New Category",
                onClick = {
                    viewModel.onAddNewCategoryClicked()
                }
            )
        }
    }

    if (isNewCategoryDialogVisible) {
        AddCategoryDialog(
                onAddNewCategory = { categoryName, categoryDescription ->
                    viewModel.addNewCategory(categoryName, categoryDescription)
                },
        onDismiss = {
            viewModel.onAddNewCategoryClicked()
        }
        )
    }

    if (isEditCategoryDialogVisible && selectedCategory != null){
        EditCategoryDialog(
            category = selectedCategory!!,
            onEditCategory = { category ->
                viewModel.updateCategory(category)

            },
            onDeleteCategory = {},
            onDismiss = {
                viewModel.onEditCategoryClicked(null)
            }
        )
    }
}

