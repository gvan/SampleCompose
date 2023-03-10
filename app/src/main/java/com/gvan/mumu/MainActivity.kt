package com.gvan.mumu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gvan.mumu.data.model.Video
import com.gvan.mumu.data.model.VideoAttributes
import com.gvan.mumu.ui.compose.home.HomeScreen
import com.gvan.mumu.ui.navigation.MainScreenView
import com.gvan.mumu.ui.theme.MumuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MumuTheme {
                MainScreenView()
            }
        }
    }
}