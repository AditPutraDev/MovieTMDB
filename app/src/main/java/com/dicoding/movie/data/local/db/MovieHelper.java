package com.dicoding.movie.data.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.movie.data.local.entity.EntityMovie;

import java.util.ArrayList;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static android.provider.MediaStore.MediaColumns.TITLE;
import static com.dicoding.movie.data.local.db.DatabaseContract.MovieColumns.BACKDROP_PATH;
import static com.dicoding.movie.data.local.db.DatabaseContract.MovieColumns.OVERVIEW;
import static com.dicoding.movie.data.local.db.DatabaseContract.MovieColumns.POSTER_PATH;
import static com.dicoding.movie.data.local.db.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.dicoding.movie.data.local.db.DatabaseContract.MovieColumns.VOTE_AVERAGE;
import static com.dicoding.movie.data.local.db.DatabaseContract.TABLE_MOVIE;

public class MovieHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static DatabaseHelper dataBaseHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    public MovieHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<EntityMovie> getAllMovie() {
        ArrayList<EntityMovie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        EntityMovie eMovie;
        if (cursor.getCount() > 0) {
            do {
                eMovie = new EntityMovie();
                eMovie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                eMovie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                eMovie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                eMovie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH)));
                eMovie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                eMovie.setVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                eMovie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));

                arrayList.add(eMovie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(EntityMovie eMovie) {
        ContentValues values = new ContentValues();
        values.put(TITLE, eMovie.getTitle());
        values.put(POSTER_PATH, eMovie.getPosterPath());
        values.put(BACKDROP_PATH, eMovie.getBackdropPath());
        values.put(RELEASE_DATE, eMovie.getReleaseDate());
        values.put(VOTE_AVERAGE, eMovie.getVoteAverage());
        values.put(OVERVIEW, eMovie.getOverview());
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateMovie(EntityMovie eMovie) {
        ContentValues values = new ContentValues();
        values.put(TITLE, eMovie.getTitle());
        values.put(POSTER_PATH, eMovie.getPosterPath());
        values.put(BACKDROP_PATH, eMovie.getBackdropPath());
        values.put(RELEASE_DATE, eMovie.getReleaseDate());
        values.put(VOTE_AVERAGE, eMovie.getVoteAverage());
        values.put(OVERVIEW, eMovie.getOverview());
        return database.update(DATABASE_TABLE, values, _ID + "= '" + eMovie.getId() + "'", null);
    }

    public int deleteMovie(int id) {
        return database.delete(TABLE_MOVIE, _ID + "= '" + id + "'", null);
    }
}

