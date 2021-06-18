package com.developerideas.myapplication.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerideas.myapplication.model.database.Movie
import com.developerideas.myapplication.model.server.MoviesRepository
import com.developerideas.myapplication.ui.common.ScopedViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class FavoriteViewModel(private val moviesRepository: MoviesRepository): ScopedViewModel() {

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>> get() = _movie

    init {
        initScope()
    }

    fun loadFavoriteData() {
        launch {
            _movie.value = moviesRepository.getFavorites()
        }
    }

    fun removeFavorite(movie: Movie) {
        launch {
            moviesRepository.deleteFavorite(movie.id)
            _movie.value = moviesRepository.getFavorites()
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}

class DummyMovies{
    companion object {
        var jsonList = "[{\n" +
                "\t\t\"adult\": false,\n" +
                "\t\t\"backdrop_path\": \"/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg\",\n" +
                "\t\t\"genre_ids\": [14, 28, 12, 878, 53],\n" +
                "\t\t\"id\": 460465,\n" +
                "\t\t\"originalLanguage\": \"en\",\n" +
                "\t\t\"originalTitle\": \"Mortal Kombat\",\n" +
                "\t\t\"overview\": \"Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.\",\n" +
                "\t\t\"popularity\": 7612.95,\n" +
                "\t\t\"posterPath\": \"/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg\",\n" +
                "\t\t\"releaseDate\": \"2021-04-07\",\n" +
                "\t\t\"title\": \"Mortal Kombat\",\n" +
                "\t\t\"video\": false,\n" +
                "\t\t\"voteAverage\": 7.9,\n" +
                "\t\t\"vote_count\": 1882\n" +
                "\t}, {\n" +
                "\t\t\"adult\": false,\n" +
                "\t\t\"backdrop_path\": \"/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg\",\n" +
                "\t\t\"genre_ids\": [878, 28, 18],\n" +
                "\t\t\"id\": 399566,\n" +
                "\t\t\"originalLanguage\": \"en\",\n" +
                "\t\t\"originalTitle\": \"Godzilla vs. Kong\",\n" +
                "\t\t\"overview\": \"In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.\",\n" +
                "\t\t\"popularity\": 3593.413,\n" +
                "\t\t\"posterPath\": \"/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg\",\n" +
                "\t\t\"releaseDate\": \"2021-03-24\",\n" +
                "\t\t\"title\": \"Godzilla vs. Kong\",\n" +
                "\t\t\"video\": false,\n" +
                "\t\t\"voteAverage\": 8.2,\n" +
                "\t\t\"vote_count\": 5146\n" +
                "\t}]"
        var gson = Gson()
        var listMovieType = object: TypeToken<List<Movie>>(){}.type
        fun list():List<Movie>{
            return gson.fromJson(jsonList, listMovieType)
        }
    }
}