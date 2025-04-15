package com.expense.book.view.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object DataEntry : Screen("dataEntry")
    object Sync : Screen("sync")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}