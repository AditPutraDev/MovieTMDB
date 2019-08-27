package com.dicoding.movie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.movie.MainViewModel;
import com.dicoding.movie.R;
import com.dicoding.movie.adapter.MovieAdapter;
import com.dicoding.movie.model.MovieItems;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    private RecyclerView rv;
    private MovieAdapter adapter;
    private MainViewModel mainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rvMovies);
        showRecyclerList();
    }

    private void showRecyclerList() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMovies().observe(this, getMovie);
    }

    private Observer<ArrayList<MovieItems>> getMovie = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                adapter.setDataMovie(movieItems);
            }
        }
    };
}
