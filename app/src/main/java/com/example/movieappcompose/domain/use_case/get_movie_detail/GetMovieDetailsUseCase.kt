package com.example.movieappcompose.domain.use_case.get_movie_detail

import com.example.movieappcompose.data.remote.dto.toMovieDetail
import com.example.movieappcompose.data.remote.dto.toMovieList
import com.example.movieappcompose.domain.model.Movie
import com.example.movieappcompose.domain.model.MovieDetail
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovieDetails(imdbId : String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))

        }catch (e: IOError){
            emit(Resource.Error("No internet connection"))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?:"error "))
        }
    }
}