package com.example.cinema.data.retrofit

import com.example.cinema.data.retrofit.api.RetrofitInstance
import com.example.cinema.models.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies():Response<MoviesModel>{
        return RetrofitInstance.api.getPopularMovie()
    }
}