package fpt.edu.appmanagement.ui.Chi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import fpt.edu.appmanagement.R;
import fpt.edu.appmanagement.adapter.ChiAdapter;


public class ChiFragment extends Fragment {

    private TabLayout tab;
    ViewPager viewPager;
    public ChiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi, container, false);
        TabLayout tabLayoutChi = view.findViewById(R.id.tabLayout_chi);
        ViewPager2 viewPager2Chi = view.findViewById(R.id.viewPager2_chi);
        viewPager2Chi.setUserInputEnabled(false);

        ChiAdapter adapter = new ChiAdapter(requireActivity());
        viewPager2Chi.setAdapter(adapter);
        // SET TITLE TAB-LAYOUT
        new TabLayoutMediator(tabLayoutChi, viewPager2Chi, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Loại chi");
                }
                if(position == 1){
                    tab.setText("Khoản chi");
                }
            }
        }).attach();
        return view;
    }
}