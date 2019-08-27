package com.dicoding.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("result")
    private ArrayList<MovieItem> result;

    public ArrayList<MovieItem> getResult() { return result; }
}
