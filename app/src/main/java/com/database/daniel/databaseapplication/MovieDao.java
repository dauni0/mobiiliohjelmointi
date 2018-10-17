package com.database.daniel.databaseapplication;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insert (Movie movie);

    @Delete
    void deleteOne (Movie movie);

    // Convience funktio devaamista varten
    @Query("DELETE FROM movie_table")
    void deleteAll();

    //Tästä mallia ja järjestys operaatiot kaikille
    @Query("SELECT * from movie_table ORDER BY name ASC")
    LiveData<List<Movie>> getAllMovies();
}
