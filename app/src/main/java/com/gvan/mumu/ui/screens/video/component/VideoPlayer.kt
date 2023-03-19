package com.gvan.mumu.ui.screens.video.component

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayer(
) {
    val context = LocalContext.current
    var playWhenReady by remember {
        mutableStateOf(true)
    }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri("https://api.mumu.in.ua/uploads/01_Popeye_for_President_6cb43625b2.mp4"))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = playWhenReady
            prepare()
            play()
        }
    }

    DisposableEffect(
        AndroidView(
            modifier = Modifier.fillMaxWidth().height(310.dp),
            factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
                FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        })
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}