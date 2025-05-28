package com.expense.book.view.componenets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expense.book.R
import com.expense.book.model.data.ENTRY_TYPE
import com.expense.book.ui.theme.ExpenseBookTheme


@Composable
fun TextToggleSwitch(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    firstOption: String,
    secondOption: String,
    currentSelection: ENTRY_TYPE,
    onToggle: (ENTRY_TYPE) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomChip(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(start = 0.dp, end = 4.dp),
            text = firstOption,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_24),
                    contentDescription = null
                )
            },
            isSelected = currentSelection == ENTRY_TYPE.EXPENSE,
            onClick = {
                if (enabled && currentSelection != ENTRY_TYPE.EXPENSE) {
                    onToggle(ENTRY_TYPE.EXPENSE)
                }
            })

        CustomChip(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(start = 4.dp, end = 0.dp),
            text = secondOption,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_24),
                    contentDescription = null
                )
            },
            isSelected = currentSelection == ENTRY_TYPE.INCOME,
            onClick = {
                if (enabled && currentSelection != ENTRY_TYPE.INCOME) {
                    onToggle(ENTRY_TYPE.INCOME)
                }
            })
    }
}

@Composable
fun CustomChip(
    modifier: Modifier = Modifier,
    text: String,
    icon: @Composable (() -> Unit)? = null,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center
            )
        },
        leadingIcon = {
            if (isSelected) {
                icon
            }
        },
//        border = FilterChipDefaults.filterChipBorder(
//            borderColor = MaterialTheme.colorScheme.outlineVariant,
//            selected = isSelected,
//            enabled = true
//        )
    )
}


@Preview
@Composable
fun TrendingSwitchPreview() {
    ExpenseBookTheme {
        var isFirstOptionSelected by remember { mutableStateOf(ENTRY_TYPE.EXPENSE) }

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
                currentSelection = isFirstOptionSelected,
                onToggle = { isFirstOptionSelected = it })
        }
    }
}