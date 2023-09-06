package com.gvan.mumu.ui.screens.create_channel

data class CreateChannelViewState (
    val loading: Boolean = false,
    val moveBack: Boolean = false,
    val name: String = "",
    val nameError: String = "",
    val description: String = "",
    val descriptionError: String = "",
    val imageUrl: String = "",
    val imageUrlError: String = "",
)