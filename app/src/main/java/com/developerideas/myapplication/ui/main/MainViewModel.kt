package com.developerideas.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developerideas.myapplication.model.Movie
import com.developerideas.myapplication.model.MoviesRepository
import com.developerideas.myapplication.ui.common.Event
import com.developerideas.myapplication.ui.common.Scope
import kotlinx.coroutines.launch

class MainViewModel(private val moviesRepository: MoviesRepository) : ViewModel(), Scope by Scope.Impl() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val _navigation = MutableLiveData<Event<Movie>>()
    val navigation: LiveData<Event<Movie>> get() = _navigation

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val movies: List<Movie>) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(moviesRepository.findPopularMovies().results)
        }
    }

    fun onCoarsePermissionRequester() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(moviesRepository.findPopularMovies().results)
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