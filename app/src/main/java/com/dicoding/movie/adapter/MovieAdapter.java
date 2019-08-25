package com.dicoding.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.R;
import com.dicoding.movie.model.MovieItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieItems> movieItems;

    public MovieAdapter(ArrayList<MovieItems> movieItems) {
        this.movieItems = movieItems;
    }

    public void refill(ArrayList<MovieItems> movieItems) {
        this.movieItems = new ArrayList<>();
        this.movieItems.addAll(movieItems);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.onBind(movieItems.get(position));
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDateRelease, tvVote;
        private ImageView ivPoster, ivBackdrop;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_title);
            tvDateRelease = itemView.findViewById(R.id.item_date_release);
            tvVote = itemView.findViewById(R.id.item_vote);

            ivPoster = itemView.findViewById(R.id.item_poster);
            ivBackdrop = itemView.findViewById(R.id.item_backdrop);
        }

        void onBind(MovieItems movieItems) {
            if (movieItems.getPosterPath() != null && !movieItems.getPosterPath().isEmpty()) {
                Picasso.get().load(movieItems.getPosterPath()).transform(new CropCircleTransformation()).into(ivPoster);
            }

            if (movieItems.getBackdropPath() != null && !movieItems.getBackdropPath().isEmpty()) {
                Picasso.get().load(movieItems.getPosterPath()).transform(new BlurTransformation(itemView.getContext(), 20)).into(ivBackdrop);
            }

            String title = checkTextIfNull(movieItems.getTitle());
            if (title.length() > 30) {
                tvTitle.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                tvTitle.setText(checkTextIfNull(movieItems.getTitle()));
            }

            tvDateRelease.setText(checkTextIfNull(movieItems.getReleaseDate()));
            tvVote.setText(checkTextIfNull("" + movieItems.getVoteAverage()));
        }
    }

    private String checkTextIfNull(String text) {
        if (text != null && !text.isEmpty()) {
            return text;
        } else {
            return "-";
        }
    }
}
