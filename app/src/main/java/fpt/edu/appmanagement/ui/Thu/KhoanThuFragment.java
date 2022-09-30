package fpt.edu.appmanagement.ui.Thu;

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
import fpt.edu.appmanagement.adapter.KhoanThuChiAdapter;
import fpt.edu.appmanagement.adapter.SpinerAdapter;
import fpt.edu.appmanagement.model.KhoanThuChi;
import fpt.edu.appmanagement.model.Loai;


public class KhoanThuFragment extends Fragment {
    ListView listViewKhoanThu;
    ArrayList<KhoanThuChi> list;
    ThuChiDAO thuChiDAO;
    FloatingActionButton floatingActionButton;
    KhoanThuChiAdapter adapter;
    ArrayList<Loai> loais;


    public KhoanThuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);

        listViewKhoanThu = view.findViewById(R.id.listViewKhoanThu);
        floatingActionButton = view.findViewById(R.id.floatadd);
        thuChiDAO = new ThuChiDAO(getContext());
        loadData();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogThemKhoanThu();
            }
        });
        return view;
    }

    private void loadData() {
        list = thuChiDAO.getDSKhoanThuChi("thu");
        adapter = new KhoanThuChiAdapter(list, getContext(), thuChiDAO);
        listViewKhoanThu.setAdapter(adapter);
    }


    private void showDialogThemKhoanThu(){
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        // ÁNH XẠ
        View view = inflater.inflate(R.layout.dialog_them_khoa_thu, null);
        EditText edtTien = view.findViewById(R.id.edtTienKhoanThu);
        Button btnThem = view.findViewById(R.id.btnThemKhoanThu);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        // FILL SPINNER
        Spinner spinnerLoaiThu = view.findViewById(R.id.spnLoaiThu);
        loais = thuChiDAO.getDSLoaiThuChi("thu");
        SpinerAdapter adapter = new SpinerAdapter(getContext(), loais);
        spinnerLoaiThu.setAdapter(adapter);
        // dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tien = edtTien.getText().toString();
                if(tien.length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập số tiền !", Toast.LENGTH_SHORT).show();
                }else {
                    Loai loai = (Loai) spinnerLoaiThu.getSelectedItem();
                    int maloai = loai.getMaloai();
                    KhoanThuChi khoanThuChi = new KhoanThuChi(Integer.parseInt(tien), maloai);
                    if(thuChiDAO.themMoiKhoanThuChi(khoanThuChi)){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    }else {
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