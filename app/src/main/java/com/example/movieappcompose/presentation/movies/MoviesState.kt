package com.example.movieappcompose.presentation.movies

import com.example.movieappcompose.domain.model.Movie

//ne durumda olduğumuzu tutabilmemize yarayan state flowlar calışırken kullanılıyor
data class MoviesState( val isloading:Boolean = false ,
                        val movies : List<Movie> = emptyList(),
                        val error : String = "",
                        val search : String = "batman"
                        )
