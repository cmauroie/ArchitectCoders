package com.developerideas.myapplication.ui.main

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.ActivityMainBinding
import com.developerideas.myapplication.model.server.MoviesRepository
import com.developerideas.myapplication.ui.PermissionRequester
import com.developerideas.myapplication.ui.common.app
import com.developerideas.myapplication.ui.common.getViewModel
import com.developerideas.myapplication.ui.detail.DetailActivity
import com.developerideas.myapplication.ui.common.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MoviesAdapter
    private val coarsePermissionRequester =
        PermissionRequester(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel { MainViewModel(MoviesRepository(app)) }

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        adapter = MoviesAdapter(viewModel::onMovieClicked)
        binding.recycler.adapter = adapter

        viewModel.navigation.observe(this, { event ->
            event.getContentIfNotHandled()?.let {
                startActivity<DetailActivity> {
                    putExtra(DetailActivity.MOVIE, it.id)
                }
            }
        })

        viewModel.requestLocationPermission.observe(this, Observer {
            coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequested()
            }
        })
    }

    /*private fun updateUi(model: UiModel) {
        if (model != UiModel.Loading) binding.progress.visibility = View.GONE

        when (model) {
            is UiModel.Content -> adapter.movies = model.movies
            UiModel.Loading -> binding.progress.visibility = View.VISIBLE
            UiModel.RequestLocationPermission -> coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequester()
            }
        }
    }*/

}