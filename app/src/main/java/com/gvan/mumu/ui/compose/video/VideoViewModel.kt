package com.gvan.mumu.ui.compose.video

import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.repository.VideoRepository
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
    private val videoRepository: VideoRepository
) : BaseViewModel() {
    private val _state = MutableStateFlow(VideoViewState())
    val state: StateFlow<VideoViewState> = _state

    fun fetchVideo() {
        viewModelScope.launch {
            videoRepository.getVideo(1)
                .collect { response ->
                    _state.update {
                        it.copy(video = response.data)
                    }
                }
        }
    }

}

data class VideoViewState(
    val video: Video? = null
)