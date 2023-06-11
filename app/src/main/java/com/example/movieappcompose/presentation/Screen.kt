package com.example.movieappcompose.presentation

sealed class Screen(val route : String){
    object MoviesScreen:Screen("movie_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")

}
