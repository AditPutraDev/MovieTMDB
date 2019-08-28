package com.dicoding.movie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.R;
import com.dicoding.movie.adapter.TvshowAdapter;
import com.dicoding.movie.model.TvResponse;
import com.dicoding.movie.model.TvShow;
import com.dicoding.movie.network.TvData;
import com.dicoding.movie.network.TvDataCallback;

import java.util.ArrayList;

public class TVShowFragment extends BaseFragment implements TvDataCallback {

    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private TvshowAdapter tvshowAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvshowAdapter = new TvshowAdapter(getContext());
        RecyclerView rvTvshow = view.findViewById(R.id.rvTVshow);
        rvTvshow.setHasFixedSize(true);
        rvTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTvshow.setAdapter(tvshowAdapter);

        if (savedInstanceState == null) {
            getTvData().getTvShow(TvData.URL_POPULAR, this);
        } else {
            tvShows = savedInstanceState.getParcelableArrayList(KEY_TV);
            tvshowAdapter.refill(tvShows);
        }
    }

    @Override
    public void onSuccess(TvResponse tvResponse) {
        tvShows = tvResponse.getResultsTv();
        tvshowAdapter.refill(tvShows);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_TV, tvShows);
        super.onSaveInstanceState(outState);
    }
}
