package com.anukul.paginglibrarydemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class ItemViewModel extends ViewModel {

     LiveData<PagedList<ItemModel>> itemPagedList;
     LiveData<PageKeyedDataSource<Integer, ItemModel>> liveDataSource;

    public ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemModelLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE_SIZE)
                .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory,config)).build();
    }

}
