package com.example.movieappcompose.presentation.movies

sealed class MoviesEvent {
    //yapılan aktivite burda arama yapılıyor her presentasyon için 1event 1 state 1 viewmodel yazılması gerekli.
    //evente gerek olmayan durumlar olabilir//birden fazla eventin olduğu durumlar olabilir
    data class search(val searchstring : String) : MoviesEvent()
}