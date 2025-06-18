package com.expense.book.view.navigation.screen.dashboard.navScreens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.expense.book.viewmodels.AccountsViewModel
import com.expense.book.view.componenets.AccountItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import com.expense.book.ui.theme.ExpenseBookTheme
import com.expense.book.view.componenets.AddNewButton

@Composable
fun AccountScreen(
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val allAccounts by viewModel.allAccounts.collectAsState()
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = allAccounts,
            key = { it.id }
        ) { account ->
            AccountItem(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                account = account,
                onDeleteAccount = {

                },
                onEditAccount = {

                }
            )
        }

        item {
            AddNewButton(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    ),
                text = "Add New Account",
                onClick = {
//                    viewModel.onAddNewCategoryClicked()
                }
            )
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
fun AccountScreenPreview() {
    ExpenseBookTheme {
        AccountScreen()
    }
}