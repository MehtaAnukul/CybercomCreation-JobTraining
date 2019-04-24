package com.anukul.mellofood.listener;

import android.view.View;

import com.anukul.mellofood.model.ProductModel;

public interface ProductOnItemClickListener {
    public void productOnItemClick(ProductModel productModel, View view, int position);
}
