package com.gvan.mumu.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : BaseViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState> = _state

    fun fetchVideos() {
        viewModelScope.launch {
            videoRepository.getVideos()
                .collect { response ->
                    _state.update {
                        it.copy(
                            videos = response.data
                        )
                    }
                }
        }
    }

}

data class HomeViewState(
    val videos: List<Video> = emptyList()
)