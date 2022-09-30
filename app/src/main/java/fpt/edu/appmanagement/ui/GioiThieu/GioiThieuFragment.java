package fpt.edu.appmanagement.ui.GioiThieu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import fpt.edu.appmanagement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GioiThieuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GioiThieuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GioiThieuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GioiThieuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GioiThieuFragment newInstance(String param1, String param2) {
        GioiThieuFragment fragment = new GioiThieuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
        Button btnGoi = view.findViewById(R.id.button_goi_ngay);
        Button btnGui = view.findViewById(R.id.button_gui_ngay);
        btnGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Gọi ngay ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0775098507")));
            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Gửi ngay", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                // link email
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"phuhmpd06115@fpt.edu.vn"});
                startActivity(Intent.createChooser(i, "Send mail..."));
            }
        });
        return view;

    }


}