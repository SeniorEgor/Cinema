package com.example.cinema.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cinema.REALIZATION
import com.example.cinema.data.room.repository.MoviesRepositoryImpl
import com.example.cinema.models.MovieItemModel

class FavoriteFragmentViewModel: ViewModel() {

    fun getAllMovies():LiveData<List<MovieItemModel>>{
        return REALIZATION.allMovies
    }
}