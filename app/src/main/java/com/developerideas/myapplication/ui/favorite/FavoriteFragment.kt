package com.developerideas.myapplication.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
//import com.developerideas.data.repository.MoviesRepository
//import com.developerideas.data.repository.RegionRepository
//import com.developerideas.domain.Movie
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.FragmentFavoriteBinding
//import com.developerideas.myapplication.model.AndroidPermissionChecker
//import com.developerideas.myapplication.model.PlayServicesLocationDataSource
//import com.developerideas.myapplication.model.database.RoomDataSource
//import com.developerideas.myapplication.model.server.TheMovieDbDataSource
//import com.developerideas.myapplication.ui.common.Event
import com.developerideas.myapplication.ui.common.Event.EventObserver
//import com.developerideas.myapplication.ui.common.app
import com.developerideas.myapplication.ui.common.bindingInflate
import dagger.hilt.android.AndroidEntryPoint

//import com.developerideas.myapplication.ui.common.getViewModel
//import com.developerideas.myapplication.ui.main.MainViewModel
//import com.developerideas.usecases.DeleteFavoritesMovies
//import com.developerideas.usecases.GetFavoritesMovies

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var binding: FragmentFavoriteBinding? = null
    private val viewModel: FavoriteViewModel by viewModels()
    private val movieAdapter = FavoriteAdapter{
        viewModel.removeFavorite(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = container?.bindingInflate(R.layout.fragment_favorite, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.setNavigationIcon(R.drawable.ic_back_arrow)
        binding?.toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }

        /*viewModel = getViewModel {
            val localDataSource = RoomDataSource(app.db)
            FavoriteViewModel(
                    GetFavoritesMovies(
                        MoviesRepository(
                            localDataSource,
                            TheMovieDbDataSource(),
                            RegionRepository(
                                PlayServicesLocationDataSource(app),
                                AndroidPermissionChecker(app)
                            ),
                            app.getString(R.string.api_key)
                        )
            ), DeleteFavoritesMovies(
                    MoviesRepository(
                        localDataSource,
                        TheMovieDbDataSource(),
                        RegionRepository(
                            PlayServicesLocationDataSource(app),
                            AndroidPermissionChecker(app)
                        )
                        ,app.getString(R.string.api_key)
                    )
                )
            )
        }*/

        binding?.apply {
            recycler.adapter = movieAdapter
            viewmodel = viewModel
            lifecycleOwner = this@FavoriteFragment
        }

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            movieAdapter.movies = it
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.loadFavoriteItems.observe(viewLifecycleOwner, EventObserver{
            viewModel.loadFavoriteData()
        })

    }
}