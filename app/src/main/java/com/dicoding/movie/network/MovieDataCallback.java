package com.dicoding.movie.network;

import com.dicoding.movie.model.MovieResponse;

public interface MovieDataCallback {
    void onSuccess(MovieResponse movieResponse);

    void onFailed(String error);
}
