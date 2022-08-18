package com.example.cinema.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.REALIZATION
import com.example.cinema.data.retrofit.RetrofitRepository
import com.example.cinema.data.room.MoviesRoomDatabase
import com.example.cinema.data.room.repository.MoviesRepositoryImpl
import com.example.cinema.models.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application:Application): AndroidViewModel(application) {
    private val repository = RetrofitRepository()
    val myMovie:MutableLiveData<Response<MoviesModel>> = MutableLiveData()
    val context = application

    fun getMoviesRetrofit(){
        viewModelScope.launch {
            myMovie.value = repository.getMovies()
        }
    }

    fun initDatabase(){
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryImpl(daoMovie)
    }

}