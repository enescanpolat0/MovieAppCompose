package com.example.movieappcompose.data.dependencyinjection

import com.example.movieappcompose.data.remote.MovieAPI
import com.example.movieappcompose.data.repository.MovieRepositoryImpl
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMovieAPI():MovieAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieReposirtory(api: MovieAPI):MovieRepository{
        return MovieRepositoryImpl(api = api)
    }


}