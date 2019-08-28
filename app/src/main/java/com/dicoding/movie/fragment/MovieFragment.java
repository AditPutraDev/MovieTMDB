package com.dicoding.movie.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.R;
import com.dicoding.movie.adapter.MovieAdapter;
import com.dicoding.movie.model.Movie;
import com.dicoding.movie.model.MovieResponse;
import com.dicoding.movie.network.MovieData;
import com.dicoding.movie.network.MovieDataCallback;

import java.util.ArrayList;

public class MovieFragment extends BaseFragment implements MovieDataCallback {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieAdapter = new MovieAdapter(movies);
        RecyclerView rvMovie = view.findViewById(R.id.rvMovies);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(movieAdapter);

        if (savedInstanceState == null) {
            getMovieData().getMovies(MovieData.URL_NOW_PLAYING, this);
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            movieAdapter.refill(movies);
        }
    }

    @Override
    public void onSuccess(MovieResponse movieResponse) {
        movies = movieResponse.getResultsMovie();
        movieAdapter.refill(movies);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, movies);
        super.onSaveInstanceState(outState);
    }
}
