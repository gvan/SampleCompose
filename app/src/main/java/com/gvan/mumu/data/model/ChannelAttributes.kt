package com.gvan.mumu.data.model

data class ChannelAttributes(
    val name: String,
    val description: String,
    val isFree: Boolean,
    val views: Int,
    val image: Image
)