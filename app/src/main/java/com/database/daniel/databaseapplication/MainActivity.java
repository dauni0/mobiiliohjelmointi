package com.database.daniel.databaseapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private MovieRepository mMovieRepository;
    private List<Movie> mMovies = new ArrayList<>();

    private DatabaseReference mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieRepository = new MovieRepository(getApplication());

        //Update the UI to display database content
        //mMovieRepository.updateAllMoviesList(this, "rating");

        // Get Firebase database
        mFirebase = FirebaseDatabase.getInstance().getReference();

        mFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (mMovies != null) {
                    mMovies.clear();
                }

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                    String movieName = (String) singleSnapshot.child("name").getValue();

                    String movieRatingString = String.valueOf(singleSnapshot.child("rating").getValue());
                    Double movieRating = Double.parseDouble(movieRatingString);

                    String movieRuntimeString = String.valueOf(singleSnapshot.child("runtime").getValue());
                    Integer movieRuntime = Integer.parseInt(movieRuntimeString);

                    String movieReleaseyearString = String.valueOf(singleSnapshot.child("releaseYear").getValue());
                    Integer movieReleaseyear = Integer.parseInt(movieReleaseyearString);

                    Log.d("MYFIREBASETAG", "Got casted movie name: " + movieName);

                    Movie myMovie = new Movie(movieName, movieRating, movieRuntime, movieReleaseyear);

                    mMovies.add(myMovie);
                }

                Log.d("MYFIREBASETAG", "Updating UI with movies");
                updateMovieUI(mMovies);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (mMovies != null) {
                    mMovies.clear();
                }

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                    String movieName = (String) singleSnapshot.child("name").getValue();

                    String movieRatingString = String.valueOf(singleSnapshot.child("rating").getValue());
                    Double movieRating = Double.parseDouble(movieRatingString);

                    String movieRuntimeString = String.valueOf(singleSnapshot.child("runtime").getValue());
                    Integer movieRuntime = Integer.parseInt(movieRuntimeString);

                    String movieReleaseyearString = String.valueOf(singleSnapshot.child("releaseYear").getValue());
                    Integer movieReleaseyear = Integer.parseInt(movieReleaseyearString);

                    Log.d("MYFIREBASETAG", "Got casted movie name: " + movieName);

                    Movie myMovie = new Movie(movieName, movieRating, movieRuntime, movieReleaseyear);

                    mMovies.add(myMovie);
                }

                Log.d("MYFIREBASETAG", "Updating UI with movies");
                updateMovieUI(mMovies);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addNewMovie(View view) {

        //Get view by id and convert to movie object

        EditText newMovieName = findViewById(R.id.movieName);
        String newMovieNameString = newMovieName.getText().toString().trim();

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

        //mMovieRepository.insert(movie);

        mFirebase.child("movies").child(movie.getName()).setValue(movie);

        Log.d("MYTAG", "Adding movie: " + movie.getName());

        updateMovieUI(mMovies);

        //mMovieRepository.updateAllMoviesList(this, "name");
    }

    public void deleteMovieByName(View view) {

        EditText deleteMovieName = findViewById(R.id.movieName);
        String deleteMovieNameString = deleteMovieName.getText().toString();

        //mMovieRepository.deleteByName(deleteMovieNameString);

        mFirebase.child("movies").child(deleteMovieNameString).removeValue();

        //mMovieRepository.updateAllMoviesList(this, "name");

        updateMovieUI(mMovies);

    }

    public void deleteMovie(String movieName) {

        //mMovieRepository.deleteByName(movieName);

        mFirebase.child("movies").child(movieName).removeValue();

        //mMovieRepository.updateAllMoviesList(this, "name");

        updateMovieUI(mMovies);
    }

    public void showAllMovies(View view) {

        //get all movies

        //mMovieRepository.updateAllMoviesList(this, "name");
    }

    public void deleteAllMovies(View view) {

        mFirebase.child("movies").removeValue();

        //mMovieRepository.deleteAll();

        updateMovieUI(mMovies);

        //mMovieRepository.updateAllMoviesList(this, "name");
    }

    public void orderMoviesName(View view) {
        //mMovieRepository.updateAllMoviesList(this, "name");

        if (mMovies != null) {
            Log.d("MYFIREBASETAG", "Clearing movies");
            mMovies.clear();
        }

        mFirebase.child("movies").orderByChild("name").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String movieName = (String) dataSnapshot.child("name").getValue();

                String movieRatingString = String.valueOf(dataSnapshot.child("rating").getValue());
                Double movieRating = Double.parseDouble(movieRatingString);

                String movieRuntimeString = String.valueOf(dataSnapshot.child("runtime").getValue());
                Integer movieRuntime = Integer.parseInt(movieRuntimeString);

                String movieReleaseyearString = String.valueOf(dataSnapshot.child("releaseYear").getValue());
                Integer movieReleaseyear = Integer.parseInt(movieReleaseyearString);

                Movie myMovie = new Movie(movieName, movieRating, movieRuntime, movieReleaseyear);

                Log.d("MYFIREBASETAG", dataSnapshot.getKey() + " is named " + myMovie.getRating());

                mMovies.add(myMovie);
                Log.d("MYFIREBASETAG", "Updating movies after sorted list by name");
                updateMovieUI(mMovies);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("MYFIREBASETAG", "Movie deleted");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MYFIREBASETAG", "Database error");
            }
        });
    }

    public void orderMoviesRating(View view) {
        //mMovieRepository.updateAllMoviesList(this, "rating");

        if (mMovies != null) {
            Log.d("MYFIREBASETAG", "Clearing movies");
            mMovies.clear();
        }

        mFirebase.child("movies").orderByChild("rating").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String movieName = (String) dataSnapshot.child("name").getValue();

                String movieRatingString = String.valueOf(dataSnapshot.child("rating").getValue());
                Double movieRating = Double.parseDouble(movieRatingString);

                String movieRuntimeString = String.valueOf(dataSnapshot.child("runtime").getValue());
                Integer movieRuntime = Integer.parseInt(movieRuntimeString);

                String movieReleaseyearString = String.valueOf(dataSnapshot.child("releaseYear").getValue());
                Integer movieReleaseyear = Integer.parseInt(movieReleaseyearString);

                Movie myMovie = new Movie(movieName, movieRating, movieRuntime, movieReleaseyear);

                Log.d("MYFIREBASETAG", dataSnapshot.getKey() + " is rated " + myMovie.getRating());

                mMovies.add(myMovie);
                Log.d("MYFIREBASETAG", "Updating movies after sorted list by rating");
                updateMovieUI(mMovies);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("MYFIREBASETAG", "Movie deleted");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MYFIREBASETAG", "Database error");
            }
        });
    }

    public void orderMoviesRuntime(View view) {
        //mMovieRepository.updateAllMoviesList(this, "runtime");

        if (mMovies != null) {
            Log.d("MYFIREBASETAG", "Clearing movies");
            mMovies.clear();
        }

        mFirebase.child("movies").orderByChild("runtime").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String movieName = (String) dataSnapshot.child("name").getValue();

                String movieRatingString = String.valueOf(dataSnapshot.child("rating").getValue());
                Double movieRating = Double.parseDouble(movieRatingString);

                String movieRuntimeString = String.valueOf(dataSnapshot.child("runtime").getValue());
                Integer movieRuntime = Integer.parseInt(movieRuntimeString);

                String movieReleaseyearString = String.valueOf(dataSnapshot.child("releaseYear").getValue());
                Integer movieReleaseyear = Integer.parseInt(movieReleaseyearString);

                Movie myMovie = new Movie(movieName, movieRating, movieRuntime, movieReleaseyear);

                Log.d("MYFIREBASETAG", dataSnapshot.getKey() + " is runtime " + myMovie.getRating());

                mMovies.add(myMovie);
                Log.d("MYFIREBASETAG", "Updating movies after sorted list by runtime");
                updateMovieUI(mMovies);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("MYFIREBASETAG", "Movie deleted");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MYFIREBASETAG", "Database error");
            }
        });
    }

    public void orderMoviesReleaseyear(View view) {
        //mMovieRepository.updateAllMoviesList(this, "releaseyear");

        if (mMovies != null) {
            Log.d("MYFIREBASETAG", "Clearing movies");
            mMovies.clear();
        }

        mFirebase.child("movies").orderByChild("releaseYear").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String movieName = (String) dataSnapshot.child("name").getValue();

                String movieRatingString = String.valueOf(dataSnapshot.child("rating").getValue());
                Double movieRating = Double.parseDouble(movieRatingString);

                String movieRuntimeString = String.valueOf(dataSnapshot.child("runtime").getValue());
                Integer movieRuntime = Integer.parseInt(movieRuntimeString);

                String movieReleaseyearString = String.valueOf(dataSnapshot.child("releaseYear").getValue());
                Integer movieReleaseyear = Integer.parseInt(movieReleaseyearString);

                Movie myMovie = new Movie(movieName, movieRating, movieRuntime, movieReleaseyear);

                Log.d("MYFIREBASETAG", dataSnapshot.getKey() + " is released " + myMovie.getRating());

                mMovies.add(myMovie);
                Log.d("MYFIREBASETAG", "Updating movies after sorted list by release year");
                updateMovieUI(mMovies);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("MYFIREBASETAG", "Movie deleted");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MYFIREBASETAG", "Database error");
            }
        });
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
