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
import androidx.compose.ui.unit.dp

@Composable
fun CategoryItem(
    categoryName: String, onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable {onClick() }
            .padding(8.dp)
            .fillMaxWidth()
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = categoryName)
    }
}