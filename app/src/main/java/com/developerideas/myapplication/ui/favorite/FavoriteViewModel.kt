package com.developerideas.myapplication.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerideas.domain.Movie
import com.developerideas.myapplication.ui.common.Event
import com.developerideas.myapplication.ui.common.ScopedViewModel
import com.developerideas.usecases.DeleteFavoritesMovies
import com.developerideas.usecases.GetFavoritesMovies
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(
    private val getFavoritesMovies: GetFavoritesMovies,
    private val deleteFavoritesMovies: DeleteFavoritesMovies
) : ScopedViewModel() {

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>> get() = _movie

    private val _loadFavoriteItems = MutableLiveData<Event<Unit>>()
    val loadFavoriteItems: LiveData<Event<Unit>> get() = _loadFavoriteItems

    init {
        initScope()
        refresh()
    }

    private fun refresh(){
        launch {
            _loadFavoriteItems.value = Event(Unit)
        }
    }

    fun loadFavoriteData() {
        launch {
            _movie.value = getFavoritesMovies.invoke()
        }
    }

    fun removeFavorite(movie: Movie) {
        launch {
            deleteFavoritesMovies.invoke(movie)
            _movie.value = getFavoritesMovies.invoke()
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}