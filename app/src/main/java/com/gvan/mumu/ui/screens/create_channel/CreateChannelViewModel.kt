package com.gvan.mumu.ui.screens.create_channel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.repository.ChannelsRepository
import com.gvan.mumu.data.repository.UploadRepository
import com.gvan.mumu.data.repository.UploadRepositoryImpl
import com.gvan.mumu.ui.base.BaseViewModel
import com.gvan.mumu.utils.Const
import com.gvan.mumu.utils.ViewState
import com.gvan.mumu.utils.getViewStateFlowForNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateChannelViewModel @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val uploadRepository: UploadRepository
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

    fun onImageChange(uri: Uri?, file: File) {
        _state.update {
            it.copy(
                imageUrl = uri.toString()
            )
        }

        //val file = uri?.path?.let { File(it) }
        Log.d(Const.TAG, "file path ${file.length()}")
        viewModelScope.launch {
            getViewStateFlowForNetworkCall { uploadRepository.uploadImage(file) }
                .collect {response ->
                    when(response) {
                        is ViewState.Loading -> {

                        }
                        is ViewState.RenderSuccess -> {
                            Log.d(Const.TAG, "upload file success")
                        }
                        is ViewState.RenderFailure -> {
                            Log.d(Const.TAG, "upload file failure ${response.throwable}")
                        }
                    }
                }
        }
    }

    fun uploadImageByUri() {

    }

    fun onCreateChannelPress() {

    }

}