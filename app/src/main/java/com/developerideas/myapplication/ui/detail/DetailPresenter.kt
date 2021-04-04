package com.developerideas.myapplication.ui.detail

import com.developerideas.myapplication.model.Movie

class DetailPresenter {

    interface View {
        fun updateUI(movie: Movie)
    }

    private var view: View? = null

    fun onCreate(view: View, movie: Movie) {
        this.view = view
        view.updateUI(movie)
    }

    fun onDestroy() {
        this.view = null
    }
}