package com.expense.book.view.navigation.screen.dashboard.navScreens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CategoriesScreen(onSave: () -> Unit) {
    Button(onClick = onSave) {
        Text("Save")
    }
}