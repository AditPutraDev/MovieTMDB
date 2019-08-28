package com.dicoding.movie.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.movie.R;
import com.dicoding.movie.model.Movie;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class MDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdetail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie != null) {
            progressBar = findViewById(R.id.progress_circular);
            TextView tvTitle = findViewById(R.id.tvTitle);
            TextView tvOverview = findViewById(R.id.tvOverview);
            TextView tvRelease = findViewById(R.id.tvRelease);
            ImageView imgPoster = findViewById(R.id.imgPoster);
            ImageView imgBackdrop = findViewById(R.id.item_backdrop);

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvRelease.setText(movie.getReleaseDate());
            Picasso.get().load(movie.getPosterPath()).into(imgPoster);
            Picasso.get().load(movie.getBackdropPath()).transform(new BlurTransformation(getBaseContext(), 10)).into(imgBackdrop);
        }
    }
}
