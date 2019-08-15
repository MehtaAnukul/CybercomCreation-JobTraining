package com.anukul.paginglibrarydemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer,ItemModel>> itemModelLiveData = new MutableLiveData<>();
    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemModelLiveData.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, ItemModel>> getItemModelLiveDataSource() {
        return itemModelLiveData;
    }
}
