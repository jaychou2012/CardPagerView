package com.td.cardpagerview;

import android.app.Activity;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.OnChildViewHolderSelectedListener;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.td.cardpager.IndicatorView;
import com.td.cardpager.ViewCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ViewCardAdapter.onRecyclerItemClickerListener {
    private HorizontalGridView hgv;
    private IndicatorView indicatorView;
    private ViewCardAdapter viewCardAdapter;
    private List<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hgv = findViewById(R.id.hgv);
        indicatorView = findViewById(R.id.iv);
        listItem = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listItem.add(i + "");
        }
        viewCardAdapter = new ViewCardAdapter(this, com.td.cardpager.R.layout.item_list_hor, listItem, 300, 200, 0);
        hgv.setAdapter(viewCardAdapter);
        hgv.setNumRows(1);
        hgv.setGravity(Gravity.CENTER);
        hgv.setRowHeight(300);
        hgv.setHorizontalSpacing(0);
        hgv.setAnimateChildLayout(true);
        hgv.setSelectedPosition(listItem.size() * 100);
        viewCardAdapter.setItemListener(this);
        indicatorView.setDefaultSelect(0);
        indicatorView.setNumber(listItem.size());
        indicatorView.initLayout();
        hgv.setOnChildViewHolderSelectedListener(new OnChildViewHolderSelectedListener() {
            @Override
            public void onChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
                super.onChildViewHolderSelected(parent, child, position, subposition);
                indicatorView.setSelect(position % listItem.size());
            }
        });
    }

    @Override
    public void onRecyclerItemClick(View view, int position, List<String> list) {

    }
}
