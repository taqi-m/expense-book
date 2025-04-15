package com.expense.book.view.navigation.screen.dashboard

sealed class DashboardNavItem(val route: String, val label: String) {
    object Dashboard : DashboardNavItem("dashboard_main", "Dashboard")
    object Analysis : DashboardNavItem("analysis", "Analysis")
    object Categories : DashboardNavItem("categories", "Categories")
}