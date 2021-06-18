package com.developerideas.myapplication.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.FragmentFavoriteBinding
import com.developerideas.myapplication.model.server.MoviesRepository
import com.developerideas.myapplication.ui.common.app
import com.developerideas.myapplication.ui.common.bindingInflate
import com.developerideas.myapplication.ui.common.getViewModel

class FavoriteFragment : Fragment() {
    private var binding: FragmentFavoriteBinding? = null
    private lateinit var viewModel : FavoriteViewModel
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

        binding?.toolbar?.setNavigationIcon(R.drawable.ic_back_arrow);
        binding?.toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }

        viewModel = getViewModel {
            FavoriteViewModel(MoviesRepository(app))
        }

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@FavoriteFragment
        }

        binding?.recycler?.adapter = movieAdapter

        viewModel.movie.observe(viewLifecycleOwner, {
            movieAdapter.movies = it
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.loadFavoriteData()
    }
}