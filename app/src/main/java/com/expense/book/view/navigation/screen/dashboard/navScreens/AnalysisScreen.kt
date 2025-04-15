package com.expense.book.view.navigation.screen.dashboard.navScreens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AnalysisScreen(onBack: () -> Unit) {
    Button(onClick = onBack) {
        Text("Back")
    }
}