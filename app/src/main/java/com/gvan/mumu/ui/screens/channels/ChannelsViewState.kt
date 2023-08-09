package com.gvan.mumu.ui.screens.channels

import com.gvan.mumu.data.model.ChannelData

data class ChannelsViewState (
    val loading: Boolean = false,
    val channels: List<ChannelData> = emptyList()
)