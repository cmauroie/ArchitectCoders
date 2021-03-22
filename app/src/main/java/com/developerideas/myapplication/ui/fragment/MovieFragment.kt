package com.developerideas.myapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.FragmentMovieBinding
import com.developerideas.myapplication.model.Movie
import com.developerideas.myapplication.view.adapter.MovieAdapter
import com.developerideas.myapplication.viewmodel.MovieViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    private lateinit var movieAadapter : MovieAdapter
    private lateinit var viewModel: MovieViewModel

    private lateinit var fragmentMovieBinding: FragmentMovieBinding;





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_movie, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.refresh()

        movieAadapter = MovieAdapter()

        fragmentMovieBinding.rvMovieFragment.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = movieAadapter
        }
        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.movieList.observe(this, Observer<List<Movie>> { schedule ->
            movieAadapter.updateData(schedule)
        })


    }


}