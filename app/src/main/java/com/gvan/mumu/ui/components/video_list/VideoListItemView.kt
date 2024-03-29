package com.gvan.mumu.ui.screens.home.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gvan.mumu.data.model.Media
import com.gvan.mumu.utils.Const


@Composable
fun VideoListItem(video: Media, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth()

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)) {
            AsyncImage(
                model = Const.BASE_URL.dropLast(1) + video.attributes.image.data.attributes.formats.medium.url,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)) {
            Text(
                text = video.attributes.name
            )
            Text(
                text = video.attributes.description,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}