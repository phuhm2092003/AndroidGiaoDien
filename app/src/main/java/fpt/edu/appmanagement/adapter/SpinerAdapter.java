package fpt.edu.appmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.edu.appmanagement.R;
import fpt.edu.appmanagement.model.Loai;

public class SpinerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Loai> list;

    public SpinerAdapter(Context context, ArrayList<Loai> list) {
        this.context = context;
        this.list = list;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_spiner_adapter, null);
        }
        TextView tvSp = view.findViewById(R.id.tvSpiner);
        tvSp.setText(list.get(i).getTenloai());
        return view;
    }
}
