package com.gvan.mumu.ui.screens.channel

import com.gvan.mumu.data.repository.ChannelsRepository
import com.gvan.mumu.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val channelsRepository: ChannelsRepository
) : BaseViewModel() {
}