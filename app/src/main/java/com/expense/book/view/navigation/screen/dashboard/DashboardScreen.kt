package com.expense.book.view.navigation.screen.dashboard

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.expense.book.R
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
    // Remember selected item with Savable State to survive process death
    var selectedItem by rememberSaveable { 
        mutableStateOf(DashboardNavItem.Dashboard)
    }
    
    // Get current route to detect navigation changes
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Update selectedItem based on the current route
    currentRoute?.let { route ->
        val matchingNavItem = DashboardNavItem.entries.find { it.route == route }
        if (matchingNavItem != null && selectedItem != matchingNavItem) {
            selectedItem = matchingNavItem
        }
    }

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
                    IconButton(onClick = { 
                        // Save current tab before navigating
                        onNavigateToProfile() 
                    }) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = stringResource(R.string.profile)
                        )
                    }
                    IconButton(onClick = { 
                        // Save current tab before navigating
                        onNavigateToSettings() 
                    }) {
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
                        if (selectedItem != DashboardNavItem.Dashboard) {
                            selectedItem = DashboardNavItem.Dashboard
                            navController.navigate(DashboardNavItem.Dashboard.route) {
                                popUpTo(navController.graph.startDestinationId) { 
                                    saveState = true 
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
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
                        if (selectedItem != DashboardNavItem.Analysis) {
                            selectedItem = DashboardNavItem.Analysis
                            navController.navigate(DashboardNavItem.Analysis.route) {
                                popUpTo(navController.graph.startDestinationId) { 
                                    saveState = true 
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
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
                        if (selectedItem != DashboardNavItem.Categories) {
                            selectedItem = DashboardNavItem.Categories
                            navController.navigate(DashboardNavItem.Categories.route) {
                                popUpTo(navController.graph.startDestinationId) { 
                                    saveState = true 
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = DashboardNavItem.Dashboard.route,
            modifier = Modifier.padding(paddingValues),
            enterTransition = { fadeIn(animationSpec = tween(250)) },
            exitTransition = { fadeOut(animationSpec = tween(250)) },
            popEnterTransition = { fadeIn(animationSpec = tween(250)) },
            popExitTransition = { fadeOut(animationSpec = tween(250)) }
        ) {
            composable(DashboardNavItem.Dashboard.route) {
                DashboardMainScreen(onAddTransaction)
            }
            composable(DashboardNavItem.Analysis.route) {
                AnalysisScreen()
            }
            composable(DashboardNavItem.Categories.route) {
                CategoriesScreen()
            }
        }
    }
}
