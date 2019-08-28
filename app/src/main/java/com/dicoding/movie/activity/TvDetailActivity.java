package com.dicoding.movie.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.movie.R;
import com.dicoding.movie.model.TvShow;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class TvDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tvshow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetail);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);

        if (tvShow != null) {
            TextView tvName = findViewById(R.id.tvName);
            TextView tvOverview = findViewById(R.id.tvOverviewTv);
            TextView tvRelease = findViewById(R.id.tvReleaseTv);
            ImageView imgPoster = findViewById(R.id.imgPosterTv);
            ImageView imgBackdrop = findViewById(R.id.item_backdropTv);

            tvName.setText(tvShow.getName());
            tvOverview.setText(tvShow.getOverview());
            tvRelease.setText(tvShow.getFirstAirDate());
            Picasso.get().load(tvShow.getPosterPath()).into(imgPoster);
            Picasso.get().load(tvShow.getBackdropPath()).transform(new BlurTransformation(getBaseContext(), 10)).into(imgBackdrop);
        }
    }
}