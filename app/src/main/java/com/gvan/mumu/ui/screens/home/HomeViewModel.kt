package com.gvan.mumu.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.repository.VideoRepository
import com.gvan.mumu.domain.GetVideosUseCase
import com.gvan.mumu.ui.base.BaseViewModel
import com.gvan.mumu.utils.ViewState
import com.gvan.mumu.utils.getViewStateFlowForNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState> = _state

    fun fetchVideos() {
        viewModelScope.launch {
            getViewStateFlowForNetworkCall { getVideosUseCase.getVideos() }
                .collect { response ->
                    when(response) {
                        is ViewState.Loading -> {}
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

data class HomeViewState(
    val videos: List<Video> = emptyList()
)