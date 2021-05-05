package com.developerideas.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developerideas.myapplication.model.Movie
import com.developerideas.myapplication.model.MoviesRepository
import com.developerideas.myapplication.ui.common.Event
import com.developerideas.myapplication.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val moviesRepository: MoviesRepository) : ScopedViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _navigateToMovie = MutableLiveData<Event<Int>>()
    val navigateToMovie: LiveData<Event<Int>> get() = _navigateToMovie

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
            _movies.value = moviesRepository.findPopularMovies().results
            _loading.value = false
        }
    }

    fun onMovieClicked(movie: Movie) {
        _navigateToMovie.value = Event(movie.id)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}