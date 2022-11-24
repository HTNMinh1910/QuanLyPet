package com.example.quanlypet.adapter.booking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quanlypet.ui.fragment.LichCho_Fragment;
import com.example.quanlypet.ui.fragment.LichDaHuy_Fragment;
import com.example.quanlypet.ui.fragment.LichDaXacNhan_Fragment;
import com.example.quanlypet.ui.fragment.LichDaXong_Fragment;

public class ViewPager2_Booking_Adapter extends FragmentStateAdapter {
    public ViewPager2_Booking_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment =null;
        switch (position) {
            case 0:
                fragment = LichCho_Fragment.newInstance();
                break;
            case 1:
                fragment = LichDaXacNhan_Fragment.newInstance();
                break;
            case 2:
                fragment = LichDaXong_Fragment.newInstance();
                break;
            case 3:
                fragment = LichDaHuy_Fragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
