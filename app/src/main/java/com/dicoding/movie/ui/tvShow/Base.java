package com.dicoding.movie.ui.tvShow;

import androidx.fragment.app.Fragment;

import com.dicoding.movie.network.TvData;

public class Base extends Fragment {
    final static String KEY_TV = "tv_shows";

    TvData getTvData() {
        return new TvData();
    }
}
