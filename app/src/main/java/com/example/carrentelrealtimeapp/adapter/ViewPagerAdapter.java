package com.example.carrentelrealtimeapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.carrentelrealtimeapp.fragment.HomeFragment;
import com.example.carrentelrealtimeapp.fragment.MeFragment;
import com.example.carrentelrealtimeapp.fragment.PhieuMuonFragment;
import com.example.carrentelrealtimeapp.fragment.KhanhHangFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new KhanhHangFragment();
            case 2:
                return new PhieuMuonFragment();
            case 3:
                return new MeFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}

