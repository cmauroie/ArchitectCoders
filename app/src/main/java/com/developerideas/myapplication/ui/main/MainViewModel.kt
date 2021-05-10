package com.developerideas.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerideas.myapplication.model.database.Movie
import com.developerideas.myapplication.model.server.MoviesRepository
import com.developerideas.myapplication.ui.common.Event
import com.developerideas.myapplication.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val moviesRepository: MoviesRepository) : ScopedViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _navigation = MutableLiveData<Event<Movie>>()
    val navigation: LiveData<Event<Movie>> get() = _navigation

    private val _requestLocationPermission = MutableLiveData<Event<Unit>>()
    val requestLocationPermission: LiveData<Event<Unit>> get() = _requestLocationPermission

    init {
        initScope()
        refresh()
    }

    private fun refresh() {
        _requestLocationPermission.value = Event(Unit)
    }

    fun onCoarsePermissionRequested() {
        launch {
            _loading.value = true
            _movies.value = moviesRepository.findPopularMovies()
            _loading.value = false
        }
    }

    fun onMovieClicked(movie: Movie) {
        _navigation.value = Event(movie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}