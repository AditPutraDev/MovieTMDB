package com.dicoding.movie.ui.movie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.R;
import com.dicoding.movie.data.Movie;
import com.dicoding.movie.data.MovieResponse;
import com.dicoding.movie.network.MovieData;
import com.dicoding.movie.network.MovieDataCallback;

import java.util.ArrayList;

public class MovieFragment extends BaseFragment implements MovieDataCallback {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieAdapter = new MovieAdapter(getContext());
        RecyclerView rvMovie = view.findViewById(R.id.rvMovies);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(movieAdapter);
        progressBar = view.findViewById(R.id.progress_circular);

        if (savedInstanceState == null) {
            getMovieData().getMovies(MovieData.URL_NOW_PLAYING, this);
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            movieAdapter.refill(movies);
        }
    }

    @Override
    public void onSuccess(MovieResponse movieResponse) {
        progressBar.setVisibility(View.GONE);
        movies = movieResponse.getResultsMovie();
        movieAdapter.refill(movies);
    }

    @Override
    public void onFailed(String error) {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, movies);
        super.onSaveInstanceState(outState);
    }
}
