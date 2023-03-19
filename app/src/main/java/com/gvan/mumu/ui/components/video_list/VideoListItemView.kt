package com.gvan.mumu.ui.screens.home.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.utils.Const


@Composable
fun VideoListItem(video: Video, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(16.dp)

    ) {
        AsyncImage(
            model = Const.BASE_URL.dropLast(1) + video.attributes.image.data.attributes.formats.medium.url,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = video.attributes.name
        )
        Text(
            text = video.attributes.description,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}