package com.anukul.mellofood.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ProductCustomFragmentAdapter extends FragmentStatePagerAdapter {
    public ProductCustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ProductCustomFragment productCustomFragment = new ProductCustomFragment();
        Bundle bundle = new Bundle();
        position = position + 1;
        bundle.putString(AppConstant.KEY_MESSAGE, "" +position);
        productCustomFragment.setArguments(bundle);

        return productCustomFragment;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
