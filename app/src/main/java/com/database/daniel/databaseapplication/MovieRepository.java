package com.database.daniel.databaseapplication;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MovieRepository {

    private MovieDao mMovieDao;
    //private List<Movie> mAllMovies;

    MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        //mAllMovies = mMovieDao.getAllMovies();
    }

    public void updateAllMoviesList(Activity act, String order) {
        new updateMoviesAsyncTask(mMovieDao, act, order).execute();
    }

    private static class updateMoviesAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

        private MovieDao mAsyncTaskDao;
        private List<Movie> mAllMoviesList;
        private Activity mActivity;
        private String mOrder;

        updateMoviesAsyncTask(MovieDao dao, Activity act, String order) {
            mAsyncTaskDao = dao;
            mActivity = act;
            mOrder = order;
        }

        @Override
        protected List<Movie> doInBackground(final Void... Params) {

            if (mOrder.equals("name")) {
                mAllMoviesList = mAsyncTaskDao.getAllMoviesOrderByName();
                Log.d("MYTAG", "Movies sorted by name");
            }
            else if (mOrder.equals("rating")) {
                mAllMoviesList = mAsyncTaskDao.getAllMoviesOrderByRating();
                Log.d("MYTAG", "Movies sorted by rating");
            }
            else if (mOrder.equals("runtime")) {
                mAllMoviesList = mAsyncTaskDao.getAllMoviesOrderByRuntime();
                Log.d("MYTAG", "Movies sorted by runtime");
            }
            else if (mOrder.equals("releaseyear")) {
                mAllMoviesList = mAsyncTaskDao.getAllMoviesOrderByReleaseyear();
                Log.d("MYTAG", "Movies sorted by release");
            }
            else {
                mAllMoviesList = mAsyncTaskDao.getAllMoviesOrderByName();
                Log.d("MYTAG", "Movies sorted by name by default");
            }

            //mAllMoviesList = mAsyncTaskDao.getAllMoviesOrderByName();
            return mAllMoviesList;
        }

        @Override
        protected void onPostExecute(List<Movie> mAllMoviesList) {
            super.onPostExecute(mAllMoviesList);

            ((MainActivity) mActivity).updateMovieUI(mAllMoviesList);
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

    public void deleteByName (String movieName) {
        new deleteByNameAsyncTask(mMovieDao).execute(movieName);
    }

    private static class deleteByNameAsyncTask extends AsyncTask<String, Void, Void> {

        private MovieDao mAsyncTaskDao;

        deleteByNameAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteByName(params[0]);
            return null;
        }
    }

    public void deleteAll () {
        new deleteAllAsyncTask(mMovieDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private MovieDao mAsyncTaskDao;

        deleteAllAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

}
