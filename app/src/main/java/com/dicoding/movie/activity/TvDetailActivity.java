package com.dicoding.movie.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.movie.R;
import com.dicoding.movie.model.TvShow;
import com.squareup.picasso.Picasso;

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
            ImageView imgPoster = findViewById(R.id.imgPosterTv);

            tvName.setText(tvShow.getName());
            tvOverview.setText(tvShow.getOverview());
            Picasso.get().load(tvShow.getPosterPath()).into(imgPoster);
        }
    }
}