package com.dicoding.movie.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvResponse {
    @SerializedName("results")
    private ArrayList<TvShow> results;

    public ArrayList<TvShow> getResultsTv() {
        return results;
    }
}
