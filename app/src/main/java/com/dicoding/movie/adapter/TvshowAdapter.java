package com.dicoding.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.R;
import com.dicoding.movie.model.TvShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.TvHolder> {
    private ArrayList<TvShow> listTv;

    public TvshowAdapter(ArrayList<TvShow> listTv) {
        this.listTv = listTv;
    }

    public void refill(ArrayList<TvShow> listTv) {
        this.listTv = new ArrayList<>();
        this.listTv.addAll(listTv);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv_shows, parent, false);
        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, int position) {
        holder.onBind(listTv.get(position));
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    class TvHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvDate, tvVote;
        private ImageView ivPoster, ivBackdrop;

        TvHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_name);
            tvDate = itemView.findViewById(R.id.item_first_air_date);
            tvVote = itemView.findViewById(R.id.item_vote);

            ivPoster = itemView.findViewById(R.id.item_poster);
            ivBackdrop = itemView.findViewById(R.id.item_backdrop);
        }

        void onBind(TvShow tvShow) {
            if (tvShow.getPosterPath() != null && !tvShow.getPosterPath().isEmpty()) {
                Picasso.get().load(tvShow.getPosterPath()).transform(new CropCircleTransformation()).into(ivPoster);
            }

            if (tvShow.getBackdropPath() != null && !tvShow.getBackdropPath().isEmpty()) {
                Picasso.get().load(tvShow.getPosterPath()).transform(new BlurTransformation(itemView.getContext(), 20)).into(ivBackdrop);
            }

            String name = checkTextIfNull(tvShow.getName());
            if (name.length() > 30) {
                tvName.setText(String.format("%s...", name.substring(0, 29)));
            } else {
                tvName.setText(checkTextIfNull(tvShow.getName()));
            }

            tvDate.setText(checkTextIfNull(tvShow.getFirstAirDate()));
            tvVote.setText(checkTextIfNull("" + tvShow.getVoteAverage()));
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
