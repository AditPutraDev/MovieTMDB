package com.dicoding.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.R;
import com.dicoding.movie.activity.MDetailActivity;
import com.dicoding.movie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<Movie> movies;
    private Context context;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void refill(ArrayList<Movie> movies) {
        this.movies = new ArrayList<>();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {
        holder.onBind(movies.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MDetailActivity.class);
                intent.putParcelableArrayListExtra("extra_movie", getMovies());
                intent.putExtra("extra_movie", getMovies().get(position));
                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDateRelease, tvVote;
        private ImageView ivPoster, ivBackdrop;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_title);
            tvDateRelease = itemView.findViewById(R.id.item_date_release);
            tvVote = itemView.findViewById(R.id.item_vote);
            ivPoster = itemView.findViewById(R.id.item_poster);
            ivBackdrop = itemView.findViewById(R.id.item_backdrop);
        }

        void onBind(Movie movie) {
            if (movie.getPosterPath() != null && !movie.getPosterPath().isEmpty()) {
                Picasso.get().load(movie.getPosterPath()).transform(new CropCircleTransformation()).into(ivPoster);
            }

            if (movie.getBackdropPath() != null && !movie.getBackdropPath().isEmpty()) {
                Picasso.get().load(movie.getPosterPath()).transform(new BlurTransformation(itemView.getContext(), 20)).into(ivBackdrop);
            }

            String title = checkTextIfNull(movie.getTitle());
            if (title.length() > 30) {
                tvTitle.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                tvTitle.setText(checkTextIfNull(movie.getTitle()));
            }

            tvDateRelease.setText(checkTextIfNull(movie.getReleaseDate()));
            tvVote.setText(checkTextIfNull("" + movie.getVoteAverage()));
        }

        String checkTextIfNull(String text) {
            if (text != null && !text.isEmpty()) {
                return text;
            } else {
                return "-";
            }
        }
    }
}
