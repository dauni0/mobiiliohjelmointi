package com.database.daniel.databaseapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
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
    private Double mRating;

    @NonNull
    @ColumnInfo(name = "runtime")
    private Integer mRuntime;

    @NonNull
    @ColumnInfo(name = "releaseYear")
    private Integer mReleaseYear;

    @Ignore
    private Movie () {}

    public Movie (@NonNull String name, @NonNull Double rating, @NonNull Integer runtime, @NonNull Integer releaseYear) {
        this.mName = name;
        this.mRating = rating;
        this.mRuntime = runtime;
        this.mReleaseYear = releaseYear;
    }

    public String getName() {return this.mName;}
    public Double getRating() {return this.mRating;}
    public Integer getRuntime() {return this.mRuntime;}
    public Integer getReleaseYear() {return this.mReleaseYear;}

}
