package com.gvan.mumu.ui.screens.channels

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
class ChannelsViewModel @Inject constructor(
    private val channelsRepository: ChannelsRepository
) : BaseViewModel() {
    private val _state = MutableStateFlow(ChannelsViewState())
    val state : StateFlow<ChannelsViewState> = _state

    fun fetchChannels() {
        viewModelScope.launch {
            getViewStateFlowForNetworkCall { channelsRepository.getChannels() }
                .collect {response ->
                    when(response) {
                        is ViewState.Loading -> {
                            _state.update { it.copy(loading = response.isLoading) }
                        }
                        is ViewState.RenderSuccess -> {
                            _state.update {
                                it.copy(
                                    channels = response.output.data,
                                    loading = false
                                )
                            }
                        }
                        is ViewState.RenderFailure -> {}
                    }
                }
        }
    }

}