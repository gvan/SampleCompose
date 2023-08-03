package com.gvan.mumu.ui.screens.channels.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gvan.mumu.data.model.ChannelData
import com.gvan.mumu.utils.Const

@Composable
fun ChannelsListItemView(channel: ChannelData, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClick }
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Box(modifier = Modifier
            .width(64.dp)
            .height(64.dp)) {
            AsyncImage(
                model = Const.BASE_URL.dropLast(1) + channel.attributes.image.data.attributes.formats.thumbnail.url,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)) {
            Text(text = channel.attributes.name)
        }
    }
}