package com.expense.book.view.componenets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.expense.book.model.data.database.local.entities.Income

@Composable
fun IncomeItem(income: Income, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .padding(horizontal = 8.dp)
        .clip(RoundedCornerShape(4.dp))
        .clickable { }
        .padding(8.dp)
        .fillMaxWidth()
        .height(24.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = if (income.amount % 1 == 0.0) income.amount.toInt()
                .toString() else income.amount.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
