package com.dicoding.movie.network;

import com.dicoding.movie.data.MovieResponse;

public interface MovieDataCallback {

    void onSuccess(MovieResponse movieResponse);

    void onFailed(String error);
}
