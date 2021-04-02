package com.developerideas.myapplication.ui.main

import android.os.Bundle
import android.view.View
import com.developerideas.myapplication.databinding.ActivityMainBinding
import com.developerideas.myapplication.model.Movie
import com.developerideas.myapplication.model.MoviesRepository
import com.developerideas.myapplication.ui.DetailActivity
import com.developerideas.myapplication.ui.MoviesAdapter
import com.developerideas.myapplication.ui.common.CoroutineScopeActivity
import com.developerideas.myapplication.ui.startActivity
import kotlinx.coroutines.launch

class MainActivity : CoroutineScopeActivity(), MainPresenter.View {

    private val presenter by lazy { MainPresenter(MoviesRepository(this)) }
    private val adapter = MoviesAdapter { presenter.onMovieClicked(it) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            presenter.onCreate(this@MainActivity)
            recycler.adapter = adapter
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun updateData(movies: List<Movie>) {
        adapter.movies = movies
    }

    override fun navigateTo(movie: Movie) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.MOVIE, movie)
        }
    }
}