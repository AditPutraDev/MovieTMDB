package com.dicoding.movie.ui.favorite.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dicoding.movie.ui.favorite.tabs.FavoriteMovieFragment;
import com.dicoding.movie.ui.favorite.tabs.FavoriteTvshowFragment;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FavoriteMovieFragment();
        }
        return new FavoriteTvshowFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Movie";
        }
        return "TV Show";
    }
}
