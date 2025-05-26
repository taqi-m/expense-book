package com.expense.book.view.navigation.screen.dashboard

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.expense.book.R
import com.expense.book.view.navigation.Screen
import com.expense.book.view.navigation.screen.dashboard.navScreens.AnalysisScreen
import com.expense.book.view.navigation.screen.dashboard.navScreens.CategoriesScreen
import com.expense.book.view.navigation.screen.dashboard.navScreens.DashboardMainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToProfile: () -> Unit,
    onAddTransaction: () -> Unit,
    onSyncData: () -> Unit = {},
    onNavigateToSettings: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    var selectedItem by remember { mutableStateOf<DashboardNavItem>(DashboardNavItem.Dashboard) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(selectedItem.label) },
                actions = {
                    IconButton(onClick = onSyncData) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = stringResource(R.string.sync_data)
                        )
                    }
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = stringResource(R.string.profile)
                        )
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Rounded.Home,
                            contentDescription = stringResource(R.string.dashboard)
                        )
                    },
                    label = { Text(DashboardNavItem.Dashboard.label) },
                    selected = selectedItem == DashboardNavItem.Dashboard,
                    onClick = {
                        selectedItem = DashboardNavItem.Dashboard
                        navController.navigate(DashboardNavItem.Dashboard.route) {
                            popUpTo(DashboardNavItem.Dashboard.route) { inclusive = true }
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_analytics_24),
                            contentDescription = stringResource(R.string.analysis)
                        )
                    },
                    label = { Text(DashboardNavItem.Analysis.label) },
                    selected = selectedItem == DashboardNavItem.Analysis,
                    onClick = {
                        selectedItem = DashboardNavItem.Analysis
                        navController.navigate(DashboardNavItem.Analysis.route)
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_category_24),
                            contentDescription = stringResource(R.string.categories),
                        )
                    },
                    label = { Text(DashboardNavItem.Categories.label) },
                    selected = selectedItem == DashboardNavItem.Categories,
                    onClick = {
                        selectedItem = DashboardNavItem.Categories
                        navController.navigate(DashboardNavItem.Categories.route)
                    }
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = DashboardNavItem.Dashboard.route,
            modifier = Modifier.padding(paddingValues),
        ) {
            composable(DashboardNavItem.Dashboard.route) {
                DashboardMainScreen(onAddTransaction)
            }
            composable(DashboardNavItem.Analysis.route) {
                AnalysisScreen(onBack = {
                    navController.popBackStack()
                    selectedItem = DashboardNavItem.Dashboard
                })
            }
            composable(DashboardNavItem.Categories.route) {
                CategoriesScreen(onSave = {
                    navController.popBackStack()
                    selectedItem = DashboardNavItem.Dashboard
                })
            }
        }
    }
}
