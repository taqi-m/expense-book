package com.expense.book.view.componenets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.expense.book.model.data.database.local.entities.Category

@Composable
fun EditCategoryDialog(
    category: Category,
    onEditCategory: (Category) -> Unit,
    onDeleteCategory: () -> Unit,
    onDismiss: () -> Unit
){
    val categoryName = remember { mutableStateOf(category.categoryName) }
    val categoryDescription = remember { mutableStateOf(category.description) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Edit Category"
                )
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    onClick = onDeleteCategory
                ){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    value = categoryName.value,
                    onValueChange = { categoryName.value = it },
                    label = { Text(text = "Name") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = categoryDescription.value.toString(),
                    onValueChange = { categoryDescription.value = it },
                    label = { Text(text = "Description") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onEditCategory(
                        category.copy(
                            categoryName = categoryName.value,
                            description = categoryDescription.value
                        )
                    )
                    onDismiss()
                }
            ) {
                Text(
                    text = "Save"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Cancel"
                )
            }
        }
    )
}