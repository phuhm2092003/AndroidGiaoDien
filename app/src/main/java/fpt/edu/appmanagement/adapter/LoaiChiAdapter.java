package fpt.edu.appmanagement.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fpt.edu.appmanagement.DAO.ThuChiDAO;
import fpt.edu.appmanagement.R;
import fpt.edu.appmanagement.model.Loai;

public class LoaiChiAdapter extends BaseAdapter {
    private ArrayList<Loai> list;
    private Context context;
    private ThuChiDAO thuChiDAO;

    public LoaiChiAdapter(ArrayList<Loai> list, Context context, ThuChiDAO thuChiDAO) {
        this.list = list;
        this.context = context;
        this.thuChiDAO = thuChiDAO;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewOfItemChi {
        TextView txtTen;
        ImageView ivSua, ivXoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        ViewOfItemChi viewOfItemChi;
        if (view == null) {
            viewOfItemChi = new ViewOfItemChi();
            view = inflater.inflate(R.layout.item_loai_chi, null);

            viewOfItemChi.txtTen = view.findViewById(R.id.txtTenChi);
            viewOfItemChi.ivSua = view.findViewById(R.id.ivSuaChi);
            viewOfItemChi.ivXoa = view.findViewById(R.id.ivXoaChi);

            view.setTag(viewOfItemChi);
        } else {
            viewOfItemChi = (ViewOfItemChi) view.getTag();
        }

        viewOfItemChi.txtTen.setText(list.get(i).getTenloai());
        ///
        viewOfItemChi.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSuaLoaiChi(list.get(i));
            }
        });

        // xoá loại thu
        viewOfItemChi.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCanXoa = list.get(i).getTenloai();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xoá loại chi " + tenCanXoa + ".");
                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        int idXoa = list.get(i).getMaloai();
                        if (thuChiDAO.xoaLoaiThuChi(idXoa)) {
                            Toast.makeText(context, "Xoá thành công !", Toast.LENGTH_SHORT).show();
                            reLoadData();
                        } else {
                            Toast.makeText(context, "Xoá không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int positon) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        return view;
    }
    private void showDialogSuaLoaiChi(Loai loai) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
        View view1 = inflater1.inflate(R.layout.dialog_sua_loai_chi, null);
        EditText edtSuaLoai = view1.findViewById(R.id.edtSuaTenLoai);
        Button btnSua = view1.findViewById(R.id.btnSuaLoaiChi);
        Button btnHuy = view1.findViewById(R.id.btnHuy);
        builder.setView(view1);
        edtSuaLoai.setText(loai.getTenloai());
        AlertDialog dialog = builder.create();
        dialog.show();

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edtSua = edtSuaLoai.getText().toString();
                loai.setTenloai(edtSua);
                if (thuChiDAO.capNhatLoaiThuChi(loai)) {
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    reLoadData();
                } else {
                    Toast.makeText(context, "Sửa không thành công", Toast.LENGTH_SHORT).show();
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

    private void reLoadData() {
        list.clear();
//        lấy tâtts các có trang thái chi
        list = thuChiDAO.getDSLoaiThuChi("chi");
        notifyDataSetChanged();
    }

}
