package com.gvan.mumu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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