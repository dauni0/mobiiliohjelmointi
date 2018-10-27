package com.database.daniel.databaseapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Movie movie);

    @Delete
    void deleteOne (Movie movie);

    @Query("DELETE FROM movie_table WHERE name=:movieName")
    void deleteByName(String movieName);

    // Convience funktio devaamista varten
    @Query("DELETE FROM movie_table")
    void deleteAll();

    //Tästä mallia ja järjestys operaatiot kaikille
    @Query("SELECT * from movie_table ORDER BY name ASC")
    List<Movie> getAllMoviesOrderByName();

    @Query("SELECT * from movie_table ORDER BY rating ASC")
    List<Movie> getAllMoviesOrderByRating();

    @Query("SELECT * from movie_table ORDER BY runtime ASC")
    List<Movie> getAllMoviesOrderByRuntime();

    @Query("SELECT * from movie_table ORDER BY releaseYear ASC")
    List<Movie> getAllMoviesOrderByReleaseyear();
}
