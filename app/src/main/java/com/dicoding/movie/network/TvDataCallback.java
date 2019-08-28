package com.dicoding.movie.network;

import com.dicoding.movie.model.TvResponse;

public interface TvDataCallback {
    void onSuccess(TvResponse tvResponse);

    void onFailed(String error);
}
