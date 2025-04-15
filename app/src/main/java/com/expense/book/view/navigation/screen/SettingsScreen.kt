// SettingsScreen.kt
package com.expense.book.view.navigation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.expense.book.ui.theme.ExpenseBookTheme

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    // Your settings screen content goes here
    Text(text = "Settings Screen")
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    ExpenseBookTheme {
        SettingsScreen(onBack = {})
    }
}