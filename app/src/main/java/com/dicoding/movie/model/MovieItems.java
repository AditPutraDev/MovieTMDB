package com.dicoding.movie.model;

import org.json.JSONObject;

public class MovieItems {
    private String title;
    private String poster_path;
    private String backdrop_path;
    private String release_date;

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w185/" + poster_path;
    }

    public String getBackdropPath() { return "https://image.tmdb.org/t/p/w185/" + backdrop_path; }

    public String getRelease() {
        return release_date;
    }

    public MovieItems(JSONObject object) {
        try {
            Integer id = object.getInt("id");
            String poster_path = object.getString("poster_path");
            String title = object.getString("title");
            String backdrop_path = object.getString("backdrop_path");
            String release_date = object.getString("release_date");
            String vote_average = object.getString("vote_average");
            String original_language = object.getString("original_langauge");
            String popularity = object.getString("popularity");
            String overview = object.getString("overview");

            this.title = title;
            this.poster_path = poster_path;
            this.backdrop_path = backdrop_path;
            this.release_date = release_date;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
