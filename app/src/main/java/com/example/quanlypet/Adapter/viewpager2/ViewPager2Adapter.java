package com.example.quanlypet.Adapter.viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quanlypet.ui.Fragment.BookFragment;
import com.example.quanlypet.ui.Fragment.DocterFragment;
import com.example.quanlypet.ui.Fragment.HomeFragment;
import com.example.quanlypet.ui.Fragment.PatientFragment;
import com.example.quanlypet.ui.Fragment.UsersFragment;


public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new DocterFragment();
            case 2:
                return new PatientFragment();
            case 3:
                return new BookFragment();
            case 4:
                return new UsersFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
