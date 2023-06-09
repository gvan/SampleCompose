package com.gvan.mumu.ui.components.video_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.gvan.mumu.data.model.Media
import com.gvan.mumu.ui.screens.home.list.VideoListItem

@Composable
fun VideoList(videos: List<Media>, onVideoClick: (video: Media) -> Unit) {

    LazyColumn() {
        items(
            items = videos,
            key = { it.id }
        ) { video ->
            VideoListItem(video = video) {
                onVideoClick(video)
            }
        }
    }

}