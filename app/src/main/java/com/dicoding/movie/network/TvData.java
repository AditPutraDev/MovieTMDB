package com.dicoding.movie.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.dicoding.movie.BuildConfig;
import com.dicoding.movie.data.TvResponse;

public class TvData {
    public static final String URL_POPULAR = BuildConfig.BASE_URL_TV + "popular?api_key={apiKey}&language=en-US&page=1";

    public void getTvShow(String tvEndpoint, final TvDataCallback callback) {
        AndroidNetworking.get(tvEndpoint)
                .addPathParameter("apiKey", BuildConfig.TMDB_API_KEY)
                .setTag(TvData.class)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsObject(TvResponse.class, new ParsedRequestListener<TvResponse>() {
                    @Override
                    public void onResponse(TvResponse response) {
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
