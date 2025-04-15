package com.expense.book.view.navigation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SynchronizationScreen(onBack: () -> Unit) {
    Button(onClick = onBack) {
        Text("Back")
    }
}