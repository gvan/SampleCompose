package com.gvan.mumu.ui.compose.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gvan.mumu.data.model.Video

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onVideoClick: (Video) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.fetchVideos()
    }

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