package com.dicoding.movie.network;


import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.dicoding.movie.BuildConfig;
import com.dicoding.movie.data.MovieResponse;

public class MovieData {
    public static final String URL_NOW_PLAYING = BuildConfig.BASE_URL_TMDB + "now_playing?api_key={apiKey}&language=en-US&page=1";

    public void getMovies(String movieEndpoint, final MovieDataCallback callback) {
        AndroidNetworking.get(movieEndpoint)
                .addPathParameter("apiKey", BuildConfig.TMDB_API_KEY)
                .setTag(MovieData.class)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsObject(MovieResponse.class, new ParsedRequestListener<MovieResponse>() {
                    @Override
                    public void onResponse(MovieResponse response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR", "onError: ", anError);
                        callback.onFailed("Terjadi kesalahan saat menghubungi server");
                    }
                });
    }
}

