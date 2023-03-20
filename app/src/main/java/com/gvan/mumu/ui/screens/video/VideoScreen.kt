package com.gvan.mumu.ui.screens.video

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.ui.components.video_list.VideoList
import com.gvan.mumu.ui.navigation.BottomNavItem
import com.gvan.mumu.ui.screens.video.component.VideoPlayer

@Composable
fun VideoScreen(
    navController: NavController,
    viewModel: VideoViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.fetchVideo()
        viewModel.fetchVideos()
    }

    fun onVideoClick(video: Video) {

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Video")
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
            Column(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(padding)
            ) {

                VideoPlayer()
                Text(text = state.video?.attributes?.name ?: "")
                VideoList(videos = state.videos, onVideoClick = { onVideoClick(video = it) })
            }
            if (state.loading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )

}