package com.expense.book.view.componenets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expense.book.ui.theme.ExpenseBookTheme


@Composable
fun TextToggleSwitch(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    firstOption: String,
    secondOption: String,
    isFirstSelected: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FilterChip(
            selected = isFirstSelected,
            onClick = { if (enabled && !isFirstSelected) onToggle(true) },
            label = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = firstOption,
                    textAlign = TextAlign.Center,
                )
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(4.dp)
        )

        FilterChip(
            selected = !isFirstSelected,
            onClick = { if (enabled && isFirstSelected) onToggle(false) },
            label = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = secondOption,
                    textAlign = TextAlign.Center
                )
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(4.dp),
        )
    }
}


@Preview
@Composable
fun TrendingSwitchPreview() {
    ExpenseBookTheme {
        var isFirstOptionSelected by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextToggleSwitch(
                firstOption = "Day",
                secondOption = "Week",
                isFirstSelected = isFirstOptionSelected,
                onToggle = { isFirstOptionSelected = it })
        }
    }
}