package com.example.cinema.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.REALIZATION
import com.example.cinema.models.MovieItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel:ViewModel() {

    fun insert(movieItemModel: MovieItemModel,onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.insertMovie(movieItemModel){
                onSuccess()
            }
        }
    }

    fun delete(movieItemModel: MovieItemModel,onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.deleteMovie(movieItemModel){
                onSuccess()
            }
        }
    }
}