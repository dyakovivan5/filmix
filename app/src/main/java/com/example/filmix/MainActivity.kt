package com.example.filmix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.filmix.ui.enableDarkEdgeToEdge
import com.example.filmix.ui.theme.FilmixTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableDarkEdgeToEdge()
        setContent {
            FilmixTheme {
                AppRoot()
            }
        }
    }
}
