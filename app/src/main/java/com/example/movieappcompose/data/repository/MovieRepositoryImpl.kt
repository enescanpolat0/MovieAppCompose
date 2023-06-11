package com.example.movieappcompose.data.repository

import com.example.movieappcompose.data.remote.MovieAPI
import com.example.movieappcompose.data.remote.dto.MovieDetailDto
import com.example.movieappcompose.data.remote.dto.MoviesDto
import com.example.movieappcompose.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api :MovieAPI) :MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId = imdbId)
    }
}