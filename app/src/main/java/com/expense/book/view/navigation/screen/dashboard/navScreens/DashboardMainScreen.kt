package com.expense.book.view.navigation.screen.dashboard.navScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.expense.book.R
import com.expense.book.view.componenets.ExpenseItem
import com.expense.book.view.componenets.IncomeItem
import com.expense.book.view.componenets.SectionHeader
import com.expense.book.view.componenets.SummaryCard
import com.expense.book.viewmodels.DashboardViewModel


@Composable
fun DashboardMainScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val recentIncomes by viewModel.recentIncome.collectAsState()
    val recentExpenses by viewModel.recentExpenses.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 8.dp)
    ) {
        // Section 1: Summary Cards
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .aspectRatio(4.5f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                SummaryCard(
                    title = "Income",
                    value = totalIncome,
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
                SummaryCard(
                    title = "Expense",
                    value = totalExpense,
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
//                SummaryCard(
//                    title = "Profit",
//                    value = totalIncome - totalExpense,
//                    modifier = Modifier.weight(1f).fillMaxHeight()
//                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .aspectRatio(4f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                SummaryCard(
                    title = "Profit",
                    value = totalIncome - totalExpense,
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
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
            IncomeItem(income = income, modifier = Modifier.clickable { }.padding(horizontal = 4.dp))
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
            ExpenseItem(expense = expense, modifier = Modifier.clickable { }.padding(horizontal = 4.dp))
        }
    }
}