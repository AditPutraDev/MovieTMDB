package com.dicoding.movie.ui.movie;

import androidx.fragment.app.Fragment;

import com.dicoding.movie.network.MovieData;

public class BaseFragment extends Fragment {
    final static String KEY_MOVIES = "movies";

    MovieData getMovieData() {
        return new MovieData();
    }
}
