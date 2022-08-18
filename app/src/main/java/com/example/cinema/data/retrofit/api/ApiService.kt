package com.example.cinema.data.retrofit.api

import com.example.cinema.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("3/movie/popular?api_key=8b2579b66fc1dc1b95cfd4f0d74ada5e&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>

}