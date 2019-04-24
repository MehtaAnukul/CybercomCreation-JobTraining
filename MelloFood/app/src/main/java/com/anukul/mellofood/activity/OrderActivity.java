package com.anukul.mellofood.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anukul.mellofood.adapter.OrderAdapter;
import com.anukul.mellofood.model.OrderModel;
import com.anukul.mellofood.listener.OrderOnItemClickListener;
import com.anukul.mellofood.model.ProductModel;
import com.anukul.mellofood.R;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements OrderOnItemClickListener {
    private Toolbar toolbar;
    private TextView toolbarTitleTv;

    private RecyclerView orderProductCustomRecyclerView;
    private ArrayList<OrderModel> orderProductModelArrayList;
    private OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderProductCustomRecyclerView = findViewById(R.id.activty_order_recyclerView);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        toolbarTitleTv = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        toolbarTitleTv.setText("YOUR ORDER");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ProductModel productModel = new ProductModel();
        orderProductModelArrayList = new ArrayList<>();
        orderProductModelArrayList.add(new OrderModel("Paneer","Cheese","1","$60.00"));
        orderProductModelArrayList.add(new OrderModel("Margherita","Cheese","6","$50.00"));
        orderProductModelArrayList.add(new OrderModel("Paneer","Cheese","5","$60.00"));
        orderProductModelArrayList.add(new OrderModel("Margherita","Cheese","2","$50.00"));
        orderProductModelArrayList.add(new OrderModel("Paneer","Cheese","2","$60.00"));
        orderProductModelArrayList.add(new OrderModel("Margherita","Cheese","3","$50.00"));
        orderProductModelArrayList.add(new OrderModel("Paneer","Cheese","4","$60.00"));

        orderAdapter = new OrderAdapter(orderProductModelArrayList,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orderProductCustomRecyclerView.setLayoutManager(layoutManager);
        orderProductCustomRecyclerView.setAdapter(orderAdapter);

    }

    @Override
    public void orderProductOnItemClick(OrderModel orderModel, View view, int position) {
        Toast.makeText(this, ""+orderModel.getOrderProductName(), Toast.LENGTH_SHORT).show();
    }
}
