package com.developerideas.myapplication.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.FragmentDetailBinding
import com.developerideas.myapplication.ui.common.bindingInflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    companion object {
        const val MOVIE = "id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = container?.bindingInflate(R.layout.fragment_detail, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /*viewModel = getViewModel {
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
        }*/

        Log.i("DetailFragment", "ANDROID ${args.id}")
        Log.i("DetailFragment", "ANDROID 2 ${activity?.intent?.getIntExtra(DetailFragment.MOVIE, -1)}")
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