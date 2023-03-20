package com.gvan.mumu.ui.screens.video

import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.model.Media
import com.gvan.mumu.domain.GetVideoUseCase
import com.gvan.mumu.domain.GetVideosUseCase
import com.gvan.mumu.ui.base.BaseViewModel
import com.gvan.mumu.utils.ViewState
import com.gvan.mumu.utils.getViewStateFlowForNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase,
    private val getVideoUseCase: GetVideoUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(VideoViewState())
    val state: StateFlow<VideoViewState> = _state

    fun fetchVideo(videoId: Int) {
        viewModelScope.launch {
            getViewStateFlowForNetworkCall { getVideoUseCase.getVideo(videoId) }
                .collect { response ->
                    when (response) {
                        is ViewState.Loading -> {}
                        is ViewState.RenderSuccess -> {
                            _state.update {
                                it.copy(video = response.output.data)
                            }
                        }
                        is ViewState.RenderFailure -> {}
                    }
                }
        }
    }

    fun fetchVideos() {
        viewModelScope.launch {
            getViewStateFlowForNetworkCall { getVideosUseCase.getVideos() }
                .collect { response ->
                    when (response) {
                        is ViewState.Loading -> {
                            _state.update { it.copy(loading = response.isLoading) }
                        }
                        is ViewState.RenderSuccess -> {
                            _state.update {
                                it.copy(
                                    videos = response.output.data
                                )
                            }
                        }
                        is ViewState.RenderFailure -> {}
                    }
                }
        }
    }

}

data class VideoViewState(
    val loading: Boolean = false,
    val video: Media? = null,
    val videos: List<Media> = emptyList()
)