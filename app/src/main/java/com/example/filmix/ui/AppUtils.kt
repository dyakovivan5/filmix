package com.example.filmix.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.example.filmix.MovieDetailsActivity
import com.example.filmix.R

fun ComponentActivity.enableDarkEdgeToEdge() {
    enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
    )
}

@Composable
fun currentLanguage(): String {
    return LocalConfiguration.current.locales[0].language
}

@Composable
fun genreName(genre: String): String {
    val id = when (genre) {
        "Sci-Fi" -> R.string.genre_sci_fi
        "Drama" -> R.string.genre_drama
        "Adventure" -> R.string.genre_adventure
        "Action" -> R.string.genre_action
        "Thriller" -> R.string.genre_thriller
        "Mystery" -> R.string.genre_mystery
        else -> null
    }
    return if (id != null) stringResource(id) else genre
}

fun openMovie(context: Context, movieId: Int) {
    val intent = Intent(context, MovieDetailsActivity::class.java)
    intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId)
    context.startActivity(intent)
}
