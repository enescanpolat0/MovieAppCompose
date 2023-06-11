package com.example.movieappcompose.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.example.movieappcompose.util.Constants.IMDB_ID
import com.example.movieappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
                                               private val stateHandle: SavedStateHandle) : ViewModel() {

    private val _state = mutableStateOf<MoviesDetailState>(MoviesDetailState())
    val state : State<MoviesDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }

    }


    private fun getMovieDetail(imdbId:String){
        getMovieDetailsUseCase.executeGetMovieDetails(imdbId).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value=MoviesDetailState(movie = it.data)
                }
                is Resource.Error ->{
                    _state.value= MoviesDetailState(error =it.message ?: "Error")
                }
                is Resource.Loading ->{
                    _state.value= MoviesDetailState(isloading = true)
                }

            }


        }.launchIn(viewModelScope)
    }

}