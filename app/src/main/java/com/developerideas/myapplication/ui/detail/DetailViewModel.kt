package com.developerideas.myapplication.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.developerideas.domain.Movie
import com.developerideas.myapplication.ui.common.ScopedViewModel
import com.developerideas.usecases.FindMovieById
import com.developerideas.usecases.ToggleMovieFavorite
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val findMovieById: FindMovieById,
    private val toggleMovieFavorite: ToggleMovieFavorite
) : ScopedViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _overview = MutableLiveData<String>()
    val overview: LiveData<String> get() = _overview

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> get() = _url

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> get() = _favorite

    init {
        launch {
            val movieId: Int? = savedStateHandle["id"]
            movieId?.let{
                _movie.value = findMovieById.invoke(it)
                updateUi()
            }
        }
    }

    fun onFavoriteClicked() {
        launch {
            movie.value?.let {
                _movie.value = toggleMovieFavorite.invoke(it)
                updateUi()
            }
        }
    }

    private fun updateUi() {
        movie.value?.run {
            _title.value = title
            _overview.value = overview
            _url.value =
                "https://image.tmdb.org/t/p/w500/${backdropPath}"
            _favorite.value = favorite
        }
    }
}