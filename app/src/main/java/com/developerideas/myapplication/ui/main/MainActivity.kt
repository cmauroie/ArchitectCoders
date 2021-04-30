package com.developerideas.myapplication.ui.main

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.developerideas.myapplication.databinding.ActivityMainBinding
import com.developerideas.myapplication.model.MoviesRepository
import com.developerideas.myapplication.ui.PermissionRequester
import com.developerideas.myapplication.ui.common.getViewModel
import com.developerideas.myapplication.ui.detail.DetailActivity
import com.developerideas.myapplication.ui.main.MainViewModel.UiModel
import com.developerideas.myapplication.ui.common.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var binding: ActivityMainBinding

    private val coarsePermissionRequester = PermissionRequester(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel { MainViewModel(MoviesRepository(application)) }

        adapter = MoviesAdapter {
            viewModel.onMovieClicked(it)
        }

        binding.recycler.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {
        if (model != UiModel.Loading) binding.progress.visibility = View.GONE

        when(model) {
            is UiModel.Content -> adapter.movies = model.movies
            is UiModel.Navigation -> startActivity<DetailActivity> { putExtra(DetailActivity.MOVIE, model.movie)}
            UiModel.Loading -> binding.progress.visibility = View.VISIBLE
            UiModel.RequestLocationPermission -> coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequester()
            }
        }
    }

}