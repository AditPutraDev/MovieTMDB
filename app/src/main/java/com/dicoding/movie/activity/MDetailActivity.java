package com.dicoding.movie.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.movie.R;
import com.dicoding.movie.model.Movie;
import com.squareup.picasso.Picasso;

public class MDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdetail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie != null) {
            TextView tvTitle = findViewById(R.id.tvTitle);
            TextView tvOverview = findViewById(R.id.tvOverview);
            ImageView imgPoster = findViewById(R.id.imgPoster);

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Picasso.get().load(movie.getPosterPath()).into(imgPoster);
        }
    }
}
