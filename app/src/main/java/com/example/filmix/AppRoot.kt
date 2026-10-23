package com.example.filmix

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.filmix.data.MovieRepository
import com.example.filmix.ui.components.BottomTab
import com.example.filmix.ui.components.FilmixBottomBar
import com.example.filmix.ui.screens.HomeScreen
import com.example.filmix.ui.screens.SearchScreen
import com.example.filmix.ui.theme.Background

@Composable
fun AppRoot(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val repository = remember { MovieRepository(context) }
    val movies = remember { repository.getAll() }

    var searching by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    BackHandler(enabled = searching) {
        searching = false
        query = ""
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Background,
        bottomBar = {
            FilmixBottomBar(
                selected = BottomTab.Home,
                onSelect = { selected ->
                    if (selected == BottomTab.Home) {
                        searching = false
                        query = ""
                    }
                }
            )
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        if (searching) {
            SearchScreen(
                query = query,
                onQueryChange = { query = it },
                results = repository.search(query),
                onMovieClick = { },
                modifier = contentModifier
            )
        } else {
            HomeScreen(
                movies = movies,
                onSearchClick = { searching = true },
                onMovieClick = { },
                modifier = contentModifier
            )
        }
    }
}
