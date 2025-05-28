package com.expense.book.view.componenets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expense.book.ui.theme.ExpenseBookTheme

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryName: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable {onClick() }
            .padding(start = 16.dp)
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = categoryName)
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun CategoryItemPreview() {
    ExpenseBookTheme {
        CategoryItem(
            categoryName = "Food",
            onClick = {}
        )
    }
}