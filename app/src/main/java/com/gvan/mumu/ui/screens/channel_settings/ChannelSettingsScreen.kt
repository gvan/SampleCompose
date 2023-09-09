package com.gvan.mumu.ui.screens.channel_settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gvan.mumu.ui.navigation.BottomNavItem

@Composable
fun ChannelSettingsScreen(
    navController: NavController,
    channelId: Int,
    viewModel: ChannelSettingsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val showRemoveChannelDialog = remember { mutableStateOf(false) }

    LaunchedEffect(state.moveBack) {
        if(state.moveBack) {
            navController.popBackStack()
        }
    }

    LaunchedEffect(state.moveToChannels) {
        if(state.moveToChannels) {
            navController.popBackStack(BottomNavItem.Channels.route, inclusive = false)
        }
    }

    fun onRemoveChannelPress() {
        showRemoveChannelDialog.value = true
    }

    fun onRemoveChannelPressConfirmed() {
        viewModel.removeChannel(channelId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = if(navController.previousBackStackEntry != null) {
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
        content = {padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column() {
                    Button(onClick = { onRemoveChannelPress() }) {
                        Text(text = "Delete channel")
                    }
                }

            }

            if(state.loading) {
                Box(modifier =
                Modifier
                    .fillMaxSize()
                    .background(color = Color(0x333700B3))
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )

    if(showRemoveChannelDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showRemoveChannelDialog.value = false
            },
            buttons = {
                Row(modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround

                ) {
                    Button(
                        onClick = { showRemoveChannelDialog.value = false },
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .weight(1f)
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = {
                            showRemoveChannelDialog.value = false
                            onRemoveChannelPressConfirmed()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        Text(text = "Delete",
                            color = Color.White
                        )
                    }
            }
            },
            title = {
                Text(text = "Do you want to delete the channel?")
            },
            text = {
                Text(text = "By deleting the channel you will remove all attached videos.")
            }
            )
    }

}