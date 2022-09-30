package fpt.edu.appmanagement.ui.ThongKe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import fpt.edu.appmanagement.DAO.ThuChiDAO;
import fpt.edu.appmanagement.R;


public class ThongKeFragment extends Fragment  implements OnChartValueSelectedListener{


    public ThongKeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        PieChart mChart = view.findViewById(R.id.piechart);
        mChart = view.findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setDescription(new Description());
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Thống kê");
        mChart.setCenterTextSize(14);
        mChart.setDrawEntryLabels(true);
        addDataSet(mChart);
        mChart.setOnChartValueSelectedListener(this);
        return view;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void addDataSet(PieChart pieChart) {
        ThuChiDAO thuChiDAO = new ThuChiDAO(getContext());
        ArrayList<PieEntry> entrys = new ArrayList<>();
        float[] yData = thuChiDAO.getThongTinThuChi();
        String[] xData = {"Khoản thu"};
        for (int i = 0; i < yData.length; i++) {
            entrys.add(new PieEntry(yData[i], xData[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(entrys, " ");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(14);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#38a67c"));
        colors.add(Color.parseColor("#08432D"));
        pieDataSet.setColors(colors);
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}