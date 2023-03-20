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
import com.gvan.mumu.data.model.Media
import com.gvan.mumu.ui.components.video_list.VideoList
import com.gvan.mumu.ui.screens.video.component.VideoPlayer
import com.gvan.mumu.utils.Const

@Composable
fun VideoScreen(
    videoId: Int?,
    navController: NavController,
    viewModel: VideoViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        if(videoId != null) {
            viewModel.fetchVideo(videoId)
        }
        viewModel.fetchVideos()
    }

    fun onVideoClick(video: Media) {

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

                if(state.video != null) {
                    VideoPlayer(state.video!!.attributes.video.data.attributes.url)
                }

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