package com.dicoding.movie.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.movie.R;
import com.dicoding.movie.adapter.MovieAdapter;

public class MDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdetail);

        Parcelable[] movie = getIntent().getParcelableArrayExtra(EXTRA_MOVIE);

        if (movie != null) {
            TextView tvTitle = findViewById(R.id.tvTitle);
            TextView tvOverview = findViewById(R.id.tvOverview);
            ImageView imgPoster = findViewById(R.id.imgPoster);

            tvTitle.setText(movie.length);
        }
    }
}
