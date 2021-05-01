package com.developerideas.myapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.developerideas.myapplication.databinding.ActivityDetailBinding
import com.developerideas.myapplication.model.Movie
import com.developerideas.myapplication.ui.common.getViewModel
import com.developerideas.myapplication.ui.common.loadUrl

class DetailActivity : AppCompatActivity() {
    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie: Movie = intent.getParcelableExtra(MOVIE)
                ?: throw (IllegalStateException("Movie not found"))

        viewModel = getViewModel { DetailViewModel(movie) }

                ViewModelProvider(
                this,
                DetailViewModelFactory(movie)
        ).get(DetailViewModel::class.java)

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(binding) {
        val movie = model.movie
        movieDetailToolbar.title = movie.title
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
        movieDetailSummary.text = movie.overview
        movieDetailInfo.setMovie(movie)
    }
}