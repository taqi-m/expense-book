package com.expense.book.view.componenets

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expense.book.R
import com.expense.book.ui.theme.ExpenseBookTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryName: String,
    onEditClick: () -> Unit,
    onLongClick: () -> Unit
) {

    var menuExpanded by remember { mutableStateOf(false) }
    // fetching local context
    val mContext = LocalContext.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .combinedClickable(
                onClick = onEditClick,
                onLongClick = onLongClick
            )
            .padding(start = 16.dp)
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = categoryName
        )
        Row(

        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_circle_24),
                    contentDescription = "Edit"
                )
            }
            IconButton(
                onClick = { menuExpanded = !menuExpanded }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.more_horiz_24),
                    contentDescription = "Edit"
                )
            }
            // Creating a dropdown menu
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {

                // Creating dropdown menu item, on click
                // would create a Toast message
                DropdownMenuItem(
                    onClick = {
                        Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show()
                        onEditClick()
                        menuExpanded = false
                    },
                    text = {
                        Text(text = "Edit")
                    }
                )

                // Creating dropdown menu item, on click
                // would create a Toast message
                DropdownMenuItem(
                    onClick = {
                        Toast.makeText(
                            mContext,
                            "Delete",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    text = {
                        Text(
                            text = "Delete",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                )
            }
        }
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
            onEditClick = {},
            onLongClick = {}
        )
    }
}