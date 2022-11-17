package com.example.quanlypet.adapter.viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quanlypet.ui.fragment.AnimalFragment;
import com.example.quanlypet.ui.fragment.BookFragment;
import com.example.quanlypet.ui.fragment.DoctorFragment;
import com.example.quanlypet.ui.fragment.HomeFragment;
import com.example.quanlypet.ui.fragment.PatientFragment;
import com.example.quanlypet.ui.fragment.UsersFragment;


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
                return new DoctorFragment();
            case 2:
                return new PatientFragment();
            case 3:
                return new BookFragment();
            case 4:
                return new UsersFragment();
//            case 5:
//                return new AnimalFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
