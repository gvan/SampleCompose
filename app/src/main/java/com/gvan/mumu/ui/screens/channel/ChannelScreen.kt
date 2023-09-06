package com.gvan.mumu.ui.screens.channel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gvan.mumu.ui.navigation.BottomNavItem

@Composable
fun ChannelScreen(
    navController: NavController,
    channelId: Int,
    viewModel: ChannelViewModel = hiltViewModel()
) {

    fun onChannelSettingsPress() {
        navController.navigate("channelSettings/${channelId}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Channel")
                },
                actions = {
                    IconButton(onClick = { onChannelSettingsPress() }) {
                        Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Settings")
                    }
                },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                } else {
                    null
                }
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {

            }
        }
    )

}