package com.dicoding.movie.data.local.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "movie";

    static final class MovieColumns implements BaseColumns {

        static String TITLE = "title";
        static String RELEASE_DATE = "release_date";
        static String POSTER_PATH = "poster_path";
        static String BACKDROP_PATH = "backdrop_path";
        static String VOTE_AVERAGE = "vote_average";
        static String OVERVIEW = "overview";
    }
}
