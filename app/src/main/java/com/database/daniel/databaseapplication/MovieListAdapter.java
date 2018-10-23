package com.database.daniel.databaseapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieItemView;

        private MovieViewHolder(View itemView) {
            super(itemView);
            movieItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Movie> mMovies;

    MovieListAdapter(Context context) {mInflater = LayoutInflater.from(context); }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if (mMovies != null) {
            Movie current = mMovies.get(position);
            holder.movieItemView.setText("No movies");
        }
    }

    void setMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMovies != null)
            return mMovies.size();
        else return 0;
    }
}
