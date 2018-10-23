package com.database.daniel.databaseapplication;

import android.arch.lifecycle.LiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieRepository mMovieRepository;
    private List<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieRepository = new MovieRepository(getApplication());
        /*RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MovieListAdapter adapter = new MovieListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
    }

    public void addNewMovie(View view) {

        //Get view by id and convert to movie object

        EditText newMovieName = (EditText) findViewById(R.id.movieName);
        String newMovieNameString = newMovieName.getText().toString();
        //Integer addition_1 = Integer.parseInt(additionNumber1);

        EditText newMovieRating = (EditText) findViewById(R.id.movieRating);
        String newMovieRatingString = newMovieRating.getText().toString();
        Double newMovieRatingDouble = Double.parseDouble(newMovieRatingString);

        EditText newMovieRuntime = (EditText) findViewById(R.id.movieRuntime);
        String newMovieRuntimeString = newMovieRuntime.getText().toString();
        Integer newMovieRuntimeInt = Integer.parseInt(newMovieRuntimeString);

        EditText newMovieReleaseyear = (EditText) findViewById(R.id.movieReleaseyear);
        String newMovieReleaseyearString = newMovieReleaseyear.getText().toString();
        Integer newMovieReleaseyearInt = Integer.parseInt(newMovieReleaseyearString);

        Movie movie = new Movie(newMovieNameString, newMovieRatingDouble, newMovieRuntimeInt, newMovieReleaseyearInt);

        //MovieRepository movieRepository = new MovieRepository(getApplication());

        mMovieRepository.insert(movie);

        Log.d("MYTAG", "Adding movie: " + movie.getName());

        //List<Movie> movieList =  mMovieRepository.getAllMovies();

        Log.d("MYTAG", "All movies got");

        mMovieRepository.updateAllMoviesList(this);
    }

    public void deleteMovie(View view) {

        //get movie

        Movie movie = new Movie("name", 9.9, 100, 1996);

        //then delete that movie

        mMovieRepository.delete(movie);
    }

    public void showAllMovies(View view) {

        //get all movies

        mMovieRepository.updateAllMoviesList(this);
    }

}
