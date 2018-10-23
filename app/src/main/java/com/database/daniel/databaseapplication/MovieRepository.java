package com.database.daniel.databaseapplication;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MovieRepository {

    private MovieDao mMovieDao;
    private List<Movie> mAllMovies;

    MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        //mAllMovies = mMovieDao.getAllMovies();
    }

    public void updateAllMoviesList(Activity act) {
        new updateMoviesAsyncTask(mMovieDao, act).execute();
    }

    private static class updateMoviesAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

        private MovieDao mAsyncTaskDao;
        private List<Movie> mAllMoviesList;
        private Activity mActivity;

        updateMoviesAsyncTask(MovieDao dao, Activity act) {
            mAsyncTaskDao = dao;
            mActivity = act;
        }

        @Override
        protected List<Movie> doInBackground(final Void... Params) {

            mAllMoviesList = mAsyncTaskDao.getAllMovies();
            return mAllMoviesList;
        }

        @Override
        protected void onPostExecute(List<Movie> mAllMoviesList) {
            super.onPostExecute(mAllMoviesList);

            TextView mMoviesView = mActivity.findViewById(R.id.moviesTextView);

            mMoviesView.setText("");

            for (Movie movie: mAllMoviesList) {
                Log.d("MYTAG", "Found in database movie: " + movie.getName());

                String movieString = movie.getName() + " " + movie.getRating().toString() + " "
                        + movie.getRuntime().toString() + " " + movie.getReleaseYear().toString() +
                        System.getProperty("line.separator");

                mMoviesView.append(movieString);
            }
        }
    }



    public void insert (Movie movie) {
        new insertAsyncTask(mMovieDao).execute(movie);
    }

    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete (Movie movie) {
        new deleteAsyncTask(mMovieDao).execute(movie);
    }

    private static class deleteAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        deleteAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.deleteOne(params[0]);
            return null;
        }
    }

}
