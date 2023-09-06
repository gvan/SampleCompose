package com.gvan.mumu.ui.screens.channels

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gvan.mumu.data.model.ChannelData
import com.gvan.mumu.ui.navigation.BottomNavItem
import com.gvan.mumu.ui.screens.channels.components.ChannelsList
import com.gvan.mumu.utils.Const

@Composable
fun ChannelsScreen(
    navController: NavController,
    viewModel: ChannelsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.fetchChannels()
    }

    fun onChannelClick(channel: ChannelData) {
        navController.navigate("channel/${channel.id}") {
            popUpTo(BottomNavItem.Channels.route)
        }
    }

    fun onCreateChannelClick() {
        navController.navigate("createChannel")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Channels")
                },
                actions = {
                    IconButton(onClick = { onCreateChannelClick() }) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add")
                    }
                }
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                ChannelsList(
                    channels = state.channels,
                    onChannelClick = { onChannelClick(channel = it) }
                )
            }
            if(state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )


}