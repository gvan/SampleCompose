package com.gvan.mumu.ui.screens.create_channel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gvan.mumu.data.model.post_params.ChannelDataParams
import com.gvan.mumu.data.model.post_params.ChannelImageParams
import com.gvan.mumu.data.model.post_params.ChannelParams
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
    var imageFile: File? = null

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
        imageFile = file
        _state.update {
            it.copy(
                imageUrl = uri.toString()
            )
        }
    }

    fun onCreateChannelPress() {
        viewModelScope.launch {
            if(imageFile == null) return@launch
            getViewStateFlowForNetworkCall { uploadRepository.uploadImage(imageFile!!) }
                .collect {response ->
                    when(response) {
                        is ViewState.Loading -> {

                        }
                        is ViewState.RenderSuccess -> {
                            Log.d(Const.TAG, "upload file success")
                            if(response.output.isNotEmpty()) {
                                val uploadedImage = response.output[0]
                                val imageId = uploadedImage.id
                                createChannel(imageId)
                            }
                        }
                        is ViewState.RenderFailure -> {
                            Log.d(Const.TAG, "upload file failure ${response.throwable}")
                        }
                    }
                }
        }
    }

    private fun createChannel(imageId: Int) {
        val params = ChannelParams(ChannelDataParams(
            name = state.value.name,
            description = state.value.description,
            image = ChannelImageParams(id = imageId)
        ))
        viewModelScope.launch {
            getViewStateFlowForNetworkCall { channelsRepository.createChannel(params) }
                .collect {response ->
                    when(response) {
                        is ViewState.Loading -> {

                        }
                        is ViewState.RenderSuccess -> {

                        }
                        is ViewState.RenderFailure -> {

                        }
                    }
                }
        }
    }

}