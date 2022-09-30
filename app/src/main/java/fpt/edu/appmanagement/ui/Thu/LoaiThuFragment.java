package fpt.edu.appmanagement.ui.Thu;

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
import fpt.edu.appmanagement.adapter.LoaiThuAdapter;
import fpt.edu.appmanagement.model.Loai;

public class LoaiThuFragment extends Fragment {
    ListView listViewLoaiThu;
    ArrayList<Loai> list;
    ThuChiDAO thuChiDAO;
    LoaiThuAdapter loaiThuAdapter;
    FloatingActionButton floatadd;
    public LoaiThuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_thu, container, false);
        thuChiDAO = new ThuChiDAO(getContext());
        listViewLoaiThu = view.findViewById(R.id.listViewLoaiThu);
        loadData();

        floatadd = view.findViewById(R.id.floatadd);
        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogThemLoaiThu();
            }
        });
        return view;
    }

    private void loadData() {
        list =  thuChiDAO.getDSLoaiThuChi("thu");
        loaiThuAdapter = new LoaiThuAdapter(list, getContext(), thuChiDAO);
        listViewLoaiThu.setAdapter(loaiThuAdapter);
    }

    public void showDialogThemLoaiThu(){
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loai_thu, null);
        // ánh xạ view
        EditText edt = view.findViewById(R.id.edtThemMoiTenLoai);
        Button btnThem = view.findViewById(R.id.btnThemLoaiThu);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai = edt.getText().toString();
                Loai loaiThem = new Loai(tenLoai, "thu");
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