package com.dicoding.movie.base;

import androidx.fragment.app.Fragment;

import com.dicoding.movie.network.MovieData;

public class BaseFragment extends Fragment {
    public static final String KEY_MOVIES = "movies";

    public MovieData getMovieData() {
        return new MovieData();
    }
}
