package fpt.edu.appmanagement.ui.Chi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpt.edu.appmanagement.DAO.ThuChiDAO;
import fpt.edu.appmanagement.R;
import fpt.edu.appmanagement.adapter.LoaiChiAdapter;
import fpt.edu.appmanagement.model.Loai;

public class LoaiChiFragment extends Fragment {
    ListView listViewLoaiChi;
    ArrayList<Loai> list;
    ThuChiDAO thuChiDAO;
    LoaiChiAdapter loaiChiAdapter;
    FloatingActionButton floatadd;
    public LoaiChiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loai_chi, container, false);
        listViewLoaiChi = view.findViewById(R.id.listViewLoaiChi);
        thuChiDAO = new ThuChiDAO(getContext());
        loadData();
        floatadd = view.findViewById(R.id.floatadd);
        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogThemLoaiChi();
            }
        });
        return view;
    }

    private void loadData() {
        list =  thuChiDAO.getDSLoaiThuChi("chi");
        loaiChiAdapter = new LoaiChiAdapter(list, getContext(), thuChiDAO);
        listViewLoaiChi.setAdapter(loaiChiAdapter);
    }

    private void showDialogThemLoaiChi(){
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loai_chi, null);
        EditText edt = view.findViewById(R.id.edtThemMoiTenLoai);
        Button btnThem = view.findViewById(R.id.btnThemLoaiChi);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai = edt.getText().toString();
                Loai loaiThem = new Loai(tenLoai, "chi");
                if(thuChiDAO.themMoiLoaiThuChi(loaiThem)){
                    Toast.makeText(getContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
                    loadData();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm không thành công !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}