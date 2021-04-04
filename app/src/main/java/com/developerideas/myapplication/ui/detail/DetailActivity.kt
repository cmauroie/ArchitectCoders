package com.developerideas.myapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developerideas.myapplication.databinding.ActivityDetailBinding
import com.developerideas.myapplication.model.Movie
import com.developerideas.myapplication.ui.loadUrl

class DetailActivity : AppCompatActivity(), DetailPresenter.View {
    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    private val presenter = DetailPresenter()
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Movie>(MOVIE)?.let { presenter.onCreate(view = this, movie = it) }
    }

    override fun updateUI(movie: Movie) {
        with(movie) {
            binding.movieDetailToolbar.title = title
            val background = backdropPath ?: posterPath
            binding.movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780$background")
            binding.movieDetailSummary.text = overview
            binding.movieDetailInfo.setMovie(this)
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}