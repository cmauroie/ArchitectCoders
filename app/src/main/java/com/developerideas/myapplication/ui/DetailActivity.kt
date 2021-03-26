package com.developerideas.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developerideas.myapplication.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}