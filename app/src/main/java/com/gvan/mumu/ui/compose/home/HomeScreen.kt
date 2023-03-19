package com.gvan.mumu.ui.compose.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.Display.Mode
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.ui.compose.home.list.VideoListItem
import com.gvan.mumu.ui.navigation.BottomNavItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    fun onVideoClick(video: Video) {
        navController.navigate("video") {
            popUpTo(BottomNavItem.Home.route)
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.fetchVideos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                LazyColumn() {
                    items(
                        items = state.videos,
                        key = { it.id }
                    ) { video ->
                        VideoListItem(video = video) {
                            onVideoClick(video)
                        }
                    }
                }
            }
        }
    )

}