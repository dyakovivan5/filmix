package com.example.filmix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.filmix.ui.components.BottomTab
import com.example.filmix.ui.components.FilmixBottomBar
import com.example.filmix.ui.enableDarkEdgeToEdge
import com.example.filmix.ui.openMain
import com.example.filmix.ui.screens.FavoritesScreen
import com.example.filmix.ui.theme.Background
import com.example.filmix.ui.theme.FilmixTheme

class FavoritesActivity : ComponentActivity() {

    private var refreshKey by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableDarkEdgeToEdge()

        setContent {
            FilmixTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Background,
                    bottomBar = {
                        FilmixBottomBar(
                            selected = BottomTab.Favorites,
                            onSelect = { tab ->
                                if (tab != BottomTab.Favorites) {
                                    openMain(this@FavoritesActivity, tab)
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    FavoritesScreen(
                        refreshKey = refreshKey,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshKey++
    }
}
