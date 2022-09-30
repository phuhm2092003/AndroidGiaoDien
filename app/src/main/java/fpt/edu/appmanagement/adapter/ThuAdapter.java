package fpt.edu.appmanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpt.edu.appmanagement.ui.Thu.KhoanThuFragment;
import fpt.edu.appmanagement.ui.Thu.LoaiThuFragment;

public class ThuAdapter extends FragmentStateAdapter {
    public ThuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new LoaiThuFragment();
        }
        return new KhoanThuFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
