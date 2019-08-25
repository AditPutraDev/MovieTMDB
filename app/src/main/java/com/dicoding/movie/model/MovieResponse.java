package com.dicoding.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("result")
    private ArrayList<MovieItems> result;

    public ArrayList<MovieItems> getResult() {
        return result;
    }
}
