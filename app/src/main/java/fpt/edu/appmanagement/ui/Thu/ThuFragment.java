package fpt.edu.appmanagement.ui.Thu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import fpt.edu.appmanagement.R;
import fpt.edu.appmanagement.adapter.ThuAdapter;

public class ThuFragment extends Fragment {


    public ThuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        TabLayout tabLayoutThu = view.findViewById(R.id.tabLayout_thu);
        ViewPager2 viewPager2Thu = view.findViewById(R.id.viewPager2_thu);
        viewPager2Thu.setUserInputEnabled(false); // tắt tính năng vuốt

        ThuAdapter adapter = new ThuAdapter(requireActivity());
        viewPager2Thu.setAdapter(adapter);
        // SET TITLE TAB-LAYOUT
        new TabLayoutMediator(tabLayoutThu, viewPager2Thu, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Loại thu");
                }
                if(position == 1){
                    tab.setText("Khoản thu");
                }
            }
        }).attach();
        return view;
    }
}