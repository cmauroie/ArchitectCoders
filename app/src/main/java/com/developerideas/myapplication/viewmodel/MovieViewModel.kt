package com.developerideas.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developerideas.myapplication.model.Movie

class MovieViewModel : ViewModel() {

    var movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()



    private fun getMoviesFromService(){
        var list:ArrayList<Movie> = ArrayList<Movie>()

        //
        var movie1: Movie = Movie(false,"",ArrayList<Int>(),0,"","Title1","",0.0,
        "","","",false,0.0,1)

        var movie2: Movie = Movie(false,"",ArrayList<Int>(),0,"","Title2","",0.0,
            "","","",false,0.0,1)

        var movie3: Movie = Movie(false,"",ArrayList<Int>(),0,"","Title3","",0.0,
            "","","",false,0.0,1)

        var movie4: Movie = Movie(false,"",ArrayList<Int>(),0,"","Title4","",0.0,
            "","","",false,0.0,1)


        list.add(movie1)
        list.add(movie2)
        list.add(movie3)
        list.add(movie4)

        movieList.postValue(list)

        processFinished()

    }

    fun processFinished(){
        isLoading.value = true
    }

    fun refresh(){
        getMoviesFromService()
    }
}