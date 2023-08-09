package com.gvan.mumu.ui.screens.channels.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.gvan.mumu.data.model.ChannelData

@Composable
fun ChannelsList(channels: List<ChannelData>, onChannelClick: (channel: ChannelData) -> Unit) {

    LazyColumn() {
        items(
            items = channels,
            key = {it.id}
        ) {channel ->
            ChannelsListItemView(channel = channel) {
                onChannelClick(channel)
            }
        }
    }

}