package com.developerideas.myapplication.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.developerideas.usecases.GetPopularMovies
import com.developerideas.data.repository.MoviesRepository
import com.developerideas.data.repository.RegionRepository
import com.developerideas.data.source.LocalDataSource
import com.developerideas.data.source.RemoteDataSource
import com.developerideas.myapplication.NavHostActivity
import com.developerideas.myapplication.PermissionRequester
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.FragmentMainBinding
import com.developerideas.myapplication.model.AndroidPermissionChecker
import com.developerideas.myapplication.model.PlayServicesLocationDataSource
import com.developerideas.myapplication.model.database.RoomDataSource
import com.developerideas.myapplication.model.server.TheMovieDbDataSource

import com.developerideas.myapplication.ui.common.Event.EventObserver
import com.developerideas.myapplication.ui.common.app
import com.developerideas.myapplication.ui.common.bindingInflate
import com.developerideas.myapplication.ui.common.getViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter
    private val coarsePermissionRequester by lazy {
        PermissionRequester(
            requireActivity(),
            ACCESS_COARSE_LOCATION
        )
    }

    private lateinit var navController: NavController
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = container?.bindingInflate(R.layout.fragment_main, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        (activity as NavHostActivity).setSupportActionBar(binding?.toolbar)

        viewModel.navigateToMovie.observe(viewLifecycleOwner, EventObserver { id ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(id)
            navController.navigate(action)

        })

        viewModel.requestLocationPermission.observe(viewLifecycleOwner, EventObserver {
            coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequested()
            }
        })

        adapter = MoviesAdapter(requireContext(), viewModel::onMovieClicked)

    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            recycler.adapter = adapter
            viewmodel = viewModel
            lifecycleOwner = this@MainFragment
        }
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        menu.findItem(R.id.itemFavorite).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemFavorite -> {
                val action = MainFragmentDirections.actionMainFragmentToFavoriteFragment()
                navController.navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}