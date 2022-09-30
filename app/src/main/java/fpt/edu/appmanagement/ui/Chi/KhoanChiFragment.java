package fpt.edu.appmanagement.ui.Chi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpt.edu.appmanagement.DAO.ThuChiDAO;
import fpt.edu.appmanagement.R;
import fpt.edu.appmanagement.adapter.KhoanChiAdapter;
import fpt.edu.appmanagement.adapter.SpinerAdapter;
import fpt.edu.appmanagement.model.KhoanThuChi;
import fpt.edu.appmanagement.model.Loai;


public class KhoanChiFragment extends Fragment {
    ListView listViewKhoanChi;
    ArrayList<KhoanThuChi> list;
    ThuChiDAO thuChiDAO;
    FloatingActionButton floatingActionButton;
    KhoanChiAdapter adapter;
    ArrayList<Loai> loais;
    public KhoanChiFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khoan_chi, container, false);

        listViewKhoanChi = view.findViewById(R.id.listViewKhoanChi);
        floatingActionButton = view.findViewById(R.id.floatadd);
        thuChiDAO = new ThuChiDAO(getContext());
        loadData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogThemKhoanChi();
            }
        });
        return view;
    }
    private void loadData() {
        list = thuChiDAO.getDSKhoanThuChi("chi");
        adapter = new KhoanChiAdapter(list, getContext(), thuChiDAO);
        listViewKhoanChi.setAdapter(adapter);
    }

    private void showDialogThemKhoanChi() {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        // ÁNH XẠ
        View view = inflater.inflate(R.layout.dialog_them_khoan_chi, null);
        EditText edtTien = view.findViewById(R.id.edtTienKhoanChi);
        Button btnThem = view.findViewById(R.id.btnThemKhoanChi);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        // FILL SPINNER
        Spinner spinnerLoaiChi = view.findViewById(R.id.spnLoaiChi);
        loais = thuChiDAO.getDSLoaiThuChi("chi");
        SpinerAdapter adapter = new SpinerAdapter(getContext(), loais);
        spinnerLoaiChi.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tien = edtTien.getText().toString();
                if (tien.length() == 0) {
                    Toast.makeText(getContext(), "Vui lòng nhập số tiền !", Toast.LENGTH_SHORT).show();
                } else {
                    Loai loai = (Loai) spinnerLoaiChi.getSelectedItem(); // get oject spinner
                    int maloai = loai.getMaloai();
                    KhoanThuChi khoanThuChi = new KhoanThuChi(Integer.parseInt(tien), maloai);
                    if (thuChiDAO.themMoiKhoanThuChi(khoanThuChi)) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
                    }
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