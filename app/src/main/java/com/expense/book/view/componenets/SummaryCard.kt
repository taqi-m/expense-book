package com.expense.book.view.componenets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SummaryCard(title: String, value: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )

    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start,

            )
        Text(
            text = (if (value % 1 == 0.0) value.toInt()
                .toString() else value.toString()) + " Rs.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End
        )
    }
}