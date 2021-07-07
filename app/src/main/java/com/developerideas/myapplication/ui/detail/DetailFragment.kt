package com.developerideas.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.developerideas.data.repository.MoviesRepository
import com.developerideas.data.repository.RegionRepository
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.FragmentDetailBinding
import com.developerideas.myapplication.model.AndroidPermissionChecker
import com.developerideas.myapplication.model.PlayServicesLocationDataSource
import com.developerideas.myapplication.model.database.RoomDataSource
import com.developerideas.myapplication.model.server.TheMovieDbDataSource
import com.developerideas.myapplication.ui.common.app
import com.developerideas.myapplication.ui.common.bindingInflate
import com.developerideas.myapplication.ui.common.getViewModel
import com.developerideas.usecases.FindMovieById
import com.developerideas.usecases.ToggleMovieFavorite

class DetailFragment : Fragment() {


    private lateinit var viewModel: DetailViewModel
    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = container?.bindingInflate(R.layout.fragment_detail, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = getViewModel {
            val moviesRepository = MoviesRepository(
                RoomDataSource(app.db),
                TheMovieDbDataSource(),
                RegionRepository(
                    PlayServicesLocationDataSource(app),
                    AndroidPermissionChecker(app)
                ),
                app.getString(R.string.api_key)
            )
            DetailViewModel(
                args.id,
                FindMovieById(moviesRepository),
                ToggleMovieFavorite(moviesRepository)
            )
        }

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@DetailFragment
        }
    }

    /*private fun updateUi(model: DetailViewModel.UiModel) = with(binding) {
        val movie = model.movie
        movieDetailToolbar.title = movie.title
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
        movieDetailSummary.text = movie.overview
        movieDetailInfo.setMovie(movie)
    }*/
}