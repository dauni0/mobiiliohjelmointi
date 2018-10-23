package com.database.daniel.databaseapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository mRepository;
    private LiveData<List<Movie>> mAllMovies;

    public MovieViewModel (Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        //mAllMovies = mRepository.getAllMovies();
    }

    LiveData<List<Movie>> getAllMovies() {return mAllMovies;}

    public void insert(Movie movie) {mRepository.insert(movie);}

}
