package com.example.protocolforknobspace.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.protocolforknobspace.fragments.CallFragment;
import com.example.protocolforknobspace.fragments.ChatsFragment;
import com.example.protocolforknobspace.fragments.StatusFragment;
public class FragmentAdapter extends FragmentPagerAdapter {
    private Fragment[] childFragments;
    public FragmentAdapter(@NonNull  FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[] {
                new ChatsFragment(), //0
                new StatusFragment(), //1
                new CallFragment() //2
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new ChatsFragment();
            case 1 : return new StatusFragment();
            case 2 : return new CallFragment();
            default: return new ChatsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}
