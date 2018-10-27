package com.database.daniel.databaseapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieRepository mMovieRepository;
    private List<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieRepository = new MovieRepository(getApplication());

        //Update the UI to display database content
        mMovieRepository.updateAllMoviesList(this, "rating");
    }

    public void addNewMovie(View view) {

        //Get view by id and convert to movie object

        EditText newMovieName = findViewById(R.id.movieName);
        String newMovieNameString = newMovieName.getText().toString();

        EditText newMovieRating = findViewById(R.id.movieRating);
        String newMovieRatingString = newMovieRating.getText().toString();
        Double newMovieRatingDouble = Double.parseDouble(newMovieRatingString);

        EditText newMovieRuntime = findViewById(R.id.movieRuntime);
        String newMovieRuntimeString = newMovieRuntime.getText().toString();
        Integer newMovieRuntimeInt = Integer.parseInt(newMovieRuntimeString);

        EditText newMovieReleaseyear = findViewById(R.id.movieReleaseyear);
        String newMovieReleaseyearString = newMovieReleaseyear.getText().toString();
        Integer newMovieReleaseyearInt = Integer.parseInt(newMovieReleaseyearString);

        Movie movie = new Movie(newMovieNameString, newMovieRatingDouble, newMovieRuntimeInt, newMovieReleaseyearInt);

        mMovieRepository.insert(movie);

        Log.d("MYTAG", "Adding movie: " + movie.getName());

        mMovieRepository.updateAllMoviesList(this, "name");
    }

    public void deleteMovieByName(View view) {

        EditText deleteMovieName = findViewById(R.id.movieName);
        String deleteMovieNameString = deleteMovieName.getText().toString();

        mMovieRepository.deleteByName(deleteMovieNameString);

        mMovieRepository.updateAllMoviesList(this, "name");

    }

    public void deleteMovie(String movieName) {

        mMovieRepository.deleteByName(movieName);

        mMovieRepository.updateAllMoviesList(this, "name");

    }

    public void showAllMovies(View view) {

        //get all movies

        mMovieRepository.updateAllMoviesList(this, "name");
    }

    public void deleteAllMovies(View view) {

        mMovieRepository.deleteAll();

        mMovieRepository.updateAllMoviesList(this, "name");
    }

    public void orderMoviesName(View view) {
        mMovieRepository.updateAllMoviesList(this, "name");
    }

    public void orderMoviesRating(View view) {
        mMovieRepository.updateAllMoviesList(this, "rating");
    }

    public void orderMoviesRuntime(View view) {
        mMovieRepository.updateAllMoviesList(this, "runtime");
    }

    public void orderMoviesReleaseyear(View view) {
        mMovieRepository.updateAllMoviesList(this, "releaseyear");
    }

    public void updateMovieUI(List<Movie> mAllMoviesList) {

        TableLayout mMovieTable = findViewById(R.id.movieTable);
        mMovieTable.removeAllViews();

        if (mAllMoviesList.isEmpty()) {
            TextView tempText = new TextView(this);
            tempText.setText("Elokuvia ei ole lisätty vielä");
            TableRow mTempRow = new TableRow(this);
            mTempRow.addView(tempText);
            mMovieTable.addView(mTempRow);

        }
        else {

            for (final Movie movie : mAllMoviesList) {
                Log.d("MYTAG", "Found in database movie: " + movie.getName());

                String movieString = movie.getName() + " " + movie.getRating().toString() + " "
                        + movie.getRuntime().toString() + " " + movie.getReleaseYear().toString() +
                        System.getProperty("line.separator");

                TableRow mRow = new TableRow(this);

                //TableRow mRow = (TableRow)LayoutInflater.from(this).inflate(R.layout.tableview, null);

                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

                mRow.setLayoutParams(lp);

                final String movieName = movie.getName();

                TextView movieNameTV = new TextView(this);
                movieNameTV.setText(movie.getName());

                //((TextView)mRow.findViewById(R.id.movieTableRowName)).setText(movie.getName());

                TextView movieRatingTV = new TextView(this);
                movieRatingTV.setText(movie.getRating().toString());

                //((TextView)mRow.findViewById(R.id.movieTableRowRating)).setText(movie.getRating().toString());

                TextView movieRuntimeTV = new TextView(this);
                movieRuntimeTV.setText(movie.getRuntime().toString());

                //((TextView)mRow.findViewById(R.id.movieTableRowRuntime)).setText(movie.getRuntime().toString());

                TextView movieReleaseyearTV = new TextView(this);
                movieReleaseyearTV.setText(movie.getReleaseYear().toString());

                //((TextView)mRow.findViewById(R.id.movieTableRowReleaseyear)).setText(movie.getReleaseYear().toString());

                //Button btn = ((Button)mRow.findViewById(R.id.movieTableRowButton));

                Button btn = new Button(this);
                btn.setText("Poista");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("MYTAG", "Clicked button");

                        deleteMovie(movieName);
                    }
                });

                mRow.addView(movieNameTV);
                mRow.addView(movieRatingTV);
                mRow.addView(movieRuntimeTV);
                mRow.addView(movieReleaseyearTV);

                mRow.addView(btn);

                mMovieTable.addView(mRow);
            }
        }
    }
}
