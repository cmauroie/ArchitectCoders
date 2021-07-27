package com.developerideas.myapplication.ui.favorite

import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.RecyclerView
import com.developerideas.domain.Movie
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.ViewFavoriteBinding
import com.developerideas.myapplication.databinding.ViewMovieBinding
import com.developerideas.myapplication.ui.common.basicDiffUtil
import com.developerideas.myapplication.ui.common.bindingInflate
import com.developerideas.myapplication.ui.common.inflate
import com.developerideas.myapplication.ui.common.loadUrl

class FavoriteAdapter(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    var movies: List<Movie> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_favorite, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, listener)
    }

    override fun getItemCount(): Int = movies.size

    /**
     * This Holder is copied from MoviesAdapter
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewFavoriteBinding.bind(view)
        fun bind(movie: Movie, listener: (Movie) -> Unit) = with(binding) {
            movieTitle.text =buildSpannedString {
                with(movie) {
                    bold { append("Original language: ") }
                    appendLine(originalLanguage)

                    bold { append("Original title: ") }
                    appendLine(originalTitle)

                    bold { append("Release date: ") }
                    appendLine(releaseDate)

                    bold { append("Popularity: ") }
                    appendLine(popularity.toString())

                    bold { append("Vote Average: ") }
                    append(voteAverage.toString())
                }
            }
            movieCover.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
            removeItem.setOnClickListener {
                listener(movie)
            }
        }
    }


}