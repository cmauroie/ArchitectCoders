package com.developerideas.myapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.ActivityDetailBinding
import com.developerideas.myapplication.model.server.MoviesRepository
import com.developerideas.myapplication.ui.common.app
import com.developerideas.myapplication.ui.common.getViewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId: Int = intent.getIntExtra(MOVIE, -1)

        viewModel = getViewModel { DetailViewModel(movieId, MoviesRepository(app)) }

        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

    }

    /*private fun updateUi(model: DetailViewModel.UiModel) = with(binding) {
        val movie = model.movie
        movieDetailToolbar.title = movie.title
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
        movieDetailSummary.text = movie.overview
        movieDetailInfo.setMovie(movie)
    }*/
}