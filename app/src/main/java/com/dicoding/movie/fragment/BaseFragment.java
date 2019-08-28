package com.dicoding.movie.fragment;

import androidx.fragment.app.Fragment;

import com.dicoding.movie.network.MovieData;
import com.dicoding.movie.network.TvData;

public class BaseFragment extends Fragment {
    final static String KEY_MOVIES = "movies";
    final static String KEY_TV = "tv_shows";

    MovieData getMovieData() {
        return new MovieData();
    }

    TvData getTvData() {
        return new TvData();
    }

}
