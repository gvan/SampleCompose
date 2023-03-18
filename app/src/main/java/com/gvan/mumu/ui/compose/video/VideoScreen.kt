package com.gvan.mumu.ui.compose.video

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun VideoScreen(){
    Column(
        modifier = Modifier.wrapContentSize(Alignment.Center)
    ) {
        Text(text = "VideoScreen")
    }
}