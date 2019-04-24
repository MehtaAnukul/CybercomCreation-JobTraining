package com.anukul.mellofood.listener;

import android.view.View;

import com.anukul.mellofood.model.OrderModel;

public interface OrderOnItemClickListener {
    public void orderProductOnItemClick(OrderModel orderModel , View view, int position);
}
