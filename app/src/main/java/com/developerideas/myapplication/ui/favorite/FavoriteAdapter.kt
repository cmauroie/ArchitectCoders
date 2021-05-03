package com.developerideas.myapplication.ui.favorite

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.ViewMovieBinding
import com.developerideas.myapplication.model.Movie
import com.developerideas.myapplication.ui.common.basicDiffUtil
import com.developerideas.myapplication.ui.common.inflate
import com.developerideas.myapplication.ui.common.loadUrl

class FavoriteAdapter(movies:List<Movie>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>()  {

    var movies: List<Movie> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_movie, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    /**
     * This Holder is copied from MoviesAdapter
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewMovieBinding.bind(view)
        fun bind(movie: Movie) = with(binding) {
            movieTitle.text = movie.title
            movieCover.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
            val average = movie.voteAverage / 2 //score is between 0 and 10
            score.rating = average.toFloat()
        }
    }


}