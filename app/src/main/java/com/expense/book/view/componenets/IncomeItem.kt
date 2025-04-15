package com.expense.book.view.componenets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expense.book.model.data.database.local.entities.Income
import com.expense.book.ui.theme.ExpenseBookTheme

@Composable
fun IncomeItem(income: Income, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = income.buyerName, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = if (income.price % 1 == 0.0) income.price.toInt()
                .toString() else income.price.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Preview
@Composable
fun TransactionItemPreview() {
    ExpenseBookTheme {
        IncomeItem(
            income = Income(
                id = 1,
                categoryId = 1,
                buyerName = "John Doe",
                quantity = 1.0,
                price = 100.0,
                date = System.currentTimeMillis()
            )
        )
    }

}

