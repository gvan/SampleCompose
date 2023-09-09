package com.gvan.mumu.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gvan.mumu.ui.screens.channel.ChannelScreen
import com.gvan.mumu.ui.screens.channel_settings.ChannelSettingsScreen
import com.gvan.mumu.ui.screens.channels.ChannelsScreen
import com.gvan.mumu.ui.screens.create_channel.CreateChannelScreen
import com.gvan.mumu.ui.screens.home.HomeScreen
import com.gvan.mumu.ui.screens.profile.ProfileScreen
import com.gvan.mumu.ui.screens.video.VideoScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(BottomNavItem.Channels.route) {
            ChannelsScreen(navController = navController)
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
        composable(
            "video/{videoId}",
            arguments = listOf(navArgument("videoId") { type = NavType.IntType })
        ) { navBackEntry ->
            val videoId = navBackEntry.arguments?.getInt("videoId")
            VideoScreen(navController = navController, videoId = videoId)
        }
        composable("createChannel") {
            CreateChannelScreen(navController = navController)
        }
        composable(
            "channel/{channelId}",
            arguments = listOf(navArgument("channelId") { type = NavType.IntType })
        ) {navBackStackEntry ->
            val channelId = navBackStackEntry.arguments?.getInt("channelId")
            ChannelScreen(navController = navController, channelId = channelId ?: 0)
        }
        composable(
            "channelSettings/{channelId}",
            arguments = listOf(navArgument("channelId") {type = NavType.IntType})
        ) {navBackStackEntry ->
            val channelId = navBackStackEntry.arguments?.getInt("channelId")
            ChannelSettingsScreen(navController = navController, channelId = channelId ?: 0)
        }
    }
}

@Composable
fun AppBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Channels,
        BottomNavItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.LightGray
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = item.title, fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true,
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}