package com.database.daniel.databaseapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "movie_table")
public class Movie {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "rating")
    private Float mRating;

    @NonNull
    @ColumnInfo(name = "runtime")
    private Integer mRuntime;

    @NonNull
    @ColumnInfo(name = "releaseYear")
    private Integer mReleaseYear;

    public Movie (@NonNull String name, @NonNull Float rating, @NonNull Integer runtime, @NonNull Integer releaseYear) {
        this.mName = name;
        this.mRating = rating;
        this.mRuntime = runtime;
        this.mReleaseYear = releaseYear;
    }

    public String getMovieName() {return this.mName;}
    public Float getMovieRating() {return this.mRating;}
    public Integer getMovieRuntime() {return this.mRuntime;}
    public Integer getMovieReleaseYear() {return this.mReleaseYear;}

}
