package fpt.edu.appmanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpt.edu.appmanagement.ui.Chi.KhoanChiFragment;
import fpt.edu.appmanagement.ui.Chi.LoaiChiFragment;

public class ChiAdapter extends FragmentStateAdapter {
    public ChiAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0){
            return new LoaiChiFragment();
        }
        return new KhoanChiFragment();

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
