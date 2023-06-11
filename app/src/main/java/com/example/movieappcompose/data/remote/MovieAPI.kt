package com.example.movieappcompose.data.remote

import com.example.movieappcompose.data.remote.dto.MovieDetailDto
import com.example.movieappcompose.data.remote.dto.MoviesDto
import com.example.movieappcompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //http://www.omdbapi.com/?apikey=3d5346c8&i=tt3896198
    //http://www.omdbapi.com/?apikey=3d5346c8&s=batman
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString : String,
        @Query("apikey") apiKey : String = API_KEY

    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MovieDetailDto

}