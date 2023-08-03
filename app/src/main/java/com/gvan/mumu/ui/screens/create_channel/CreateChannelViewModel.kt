package com.gvan.mumu.ui.screens.create_channel

import com.gvan.mumu.data.repository.ChannelsRepository
import com.gvan.mumu.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateChannelViewModel @Inject constructor(
    private val channelsRepository: ChannelsRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow(CreateChannelViewState())
    val state : StateFlow<CreateChannelViewState> = _state

    fun onNameChange(name: String) {
        _state.update {
            it.copy(
                name = name
            )
        }
    }

    fun onDescriptionChange(description: String) {
        _state.update {
            it.copy(
                description = description
            )
        }
    }

    fun onCreateChannelPress() {

    }

    fun createChannel() {

    }

}