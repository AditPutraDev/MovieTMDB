package com.dicoding.movie.fragment;

import androidx.fragment.app.Fragment;

import com.dicoding.movie.network.MovieData;

public class BaseFragment extends Fragment {
    public final static String KEY_MOVIES = "movies";

    public MovieData getMovieData() {
        return new MovieData();
    }
}
