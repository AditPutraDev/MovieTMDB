package com.dicoding.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.R;
import com.dicoding.movie.model.MovieItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieItems> dataMovie = new ArrayList<>();

    public void setDataMovie(ArrayList<MovieItems> items) {
        dataMovie.clear();
        dataMovie.addAll(items);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(dataMovie.get(i));
    }

    @Override
    public int getItemCount() {
        return dataMovie.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterPath, backdropPath;
        TextView title, release;

        MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            release = itemView.findViewById(R.id.item_date_release);
            posterPath = itemView.findViewById(R.id.item_poster);
            backdropPath = itemView.findViewById(R.id.item_backdrop);
        }

        void bind(MovieItems movieItems) {
            title.setText(movieItems.getTitle());
            release.setText(movieItems.getRelease());
            if (movieItems.getPosterPath() != null && !movieItems.getPosterPath().isEmpty()) {
                Picasso.get().load(movieItems.getPosterPath()).transform(new CropCircleTransformation()).into(posterPath);
            }

            if (movieItems.getBackdropPath() != null && !movieItems.getBackdropPath().isEmpty()) {
                Picasso.get().load(movieItems.getPosterPath()).transform(new BlurTransformation(itemView.getContext(), 20)).into(backdropPath);
            }
        }
    }
}
