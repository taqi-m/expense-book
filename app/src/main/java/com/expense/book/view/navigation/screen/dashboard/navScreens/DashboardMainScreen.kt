package com.expense.book.view.navigation.screen.dashboard.navScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.expense.book.R
import com.expense.book.view.componenets.ExpenseItem
import com.expense.book.view.componenets.IncomeItem
import com.expense.book.view.componenets.SectionHeader
import com.expense.book.view.componenets.SummaryCard
import com.expense.book.viewmodels.DashboardViewModel


@Composable
fun DashboardMainScreen(
    onAddTransaction: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val recentIncomes by viewModel.recentIncome.collectAsState()
    val recentExpenses by viewModel.recentExpenses.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTransaction,
            ) {
                Icon(Icons.Filled.Add, stringResource(R.string.add_transaction))
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        SummaryCard("Income", totalIncome)
                        SummaryCard("Expense", totalExpense)
                        SummaryCard("Balance", totalIncome - totalExpense)
                    }
                }
            }

            // Section 2: Recent Incomes
            item {
                SectionHeader(
                    title = stringArrayResource(R.array.record_types)[0],
                    showViewAll = recentIncomes.size > 4,
                    onViewAllClick = { /*TODO*/ }
                )
            }
            items(recentIncomes.take(4)) { income ->
                IncomeItem(
                    income = income,
                    modifier = Modifier
                        .clickable { }
                        .padding(horizontal = 4.dp)
                )
            }

            // Section 3: Recent Expenses
            item {
                SectionHeader(
                    title = stringArrayResource(R.array.record_types)[1],
                    showViewAll = recentExpenses.size > 4,
                    onViewAllClick = { /*TODO*/ }
                )
            }
            items(recentExpenses.take(4)) { expense ->
                ExpenseItem(
                    expense = expense,
                    modifier = Modifier
                        .clickable { }
                        .padding(horizontal = 4.dp)
                )
            }
        }
    }
}

