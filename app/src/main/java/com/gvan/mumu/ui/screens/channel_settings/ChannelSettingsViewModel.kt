package com.gvan.mumu.ui.screens.channel_settings

import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.repository.ChannelsRepository
import com.gvan.mumu.ui.base.BaseViewModel
import com.gvan.mumu.utils.ViewState
import com.gvan.mumu.utils.getViewStateFlowForNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelSettingsViewModel @Inject constructor(
    private val channelsRepository: ChannelsRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow(ChannelSettingsViewState())
    val state : StateFlow<ChannelSettingsViewState> = _state

    fun removeChannel(channelId: Int) {
        _state.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            getViewStateFlowForNetworkCall { channelsRepository.removeChannel(channelId = channelId) }
                .collect {response ->
                    when (response) {
                        is ViewState.Loading -> {
                            _state.update { it.copy(loading = response.isLoading) }
                        }
                        is ViewState.RenderSuccess -> {
                            _state.update {
                                it.copy(
                                    loading = false,
                                    moveToChannels = true
                                )
                            }
                        }
                        is ViewState.RenderFailure -> {
                            _state.update {
                                it.copy(
                                    loading = false
                                )
                            }
                        }
                    }
                }
        }
    }

}