// EKhataApp.kt
package com.expense.book

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.expense.book.ui.theme.ExpenseBookTheme
import com.expense.book.view.navigation.Screen
import com.expense.book.view.navigation.screen.dashboard.DashboardScreen
import com.expense.book.view.navigation.screen.DataEntryScreen
import com.expense.book.view.navigation.screen.LoginScreen
import com.expense.book.view.navigation.screen.ProfileScreen
import com.expense.book.view.navigation.screen.SettingsScreen
import com.expense.book.view.navigation.screen.SynchronizationScreen

@Composable
fun EKhataApp() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}

@Composable
fun AppNavHost(navController: NavHostController) {

    //TODO: Replace startDestination with Screen.Login.route

    NavHost(navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onAddTransaction = { navController.navigate(Screen.DataEntry.route) },
                onSyncData = { navController.navigate(Screen.Sync.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.DataEntry.route) {
            DataEntryScreen(onSave = { navController.popBackStack() })
        }
        composable(Screen.Sync.route) {
            SynchronizationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Profile.route) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview(){
    ExpenseBookTheme {
        EKhataApp()
    }
}