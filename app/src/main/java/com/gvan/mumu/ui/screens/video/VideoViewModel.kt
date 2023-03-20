package com.gvan.mumu.ui.screens.video

import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.domain.GetVideoUseCase
import com.gvan.mumu.domain.GetVideosUseCase
import com.gvan.mumu.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
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

    fun fetchVideo() {
        viewModelScope.launch {
            getVideoUseCase.getVideo(1)
                .collect { response ->
                    _state.update {
                        it.copy(video = response.data)
                    }
                }
        }
    }

    fun fetchVideos() {
        viewModelScope.launch {
            getVideosUseCase.getVideos()
                .collect {response ->
                    _state.update {
                        it.copy(
                            videos = response.data
                        )
                    }
                }
        }
    }

}

data class VideoViewState(
    val video: Video? = null,
    val videos: List<Video> = emptyList()
)