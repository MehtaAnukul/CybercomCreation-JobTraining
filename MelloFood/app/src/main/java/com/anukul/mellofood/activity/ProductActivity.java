package com.anukul.mellofood.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anukul.mellofood.model.MallStallModel;
import com.anukul.mellofood.listener.MallStallOnItemClickListener;
import com.anukul.mellofood.adapter.MenuPopUpAdapter;
import com.anukul.mellofood.adapter.ProductAdapter;
import com.anukul.mellofood.model.ProductModel;
import com.anukul.mellofood.listener.ProductOnItemClickListener;
import com.anukul.mellofood.R;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductOnItemClickListener, MallStallOnItemClickListener {

    private Toolbar toolbar;
    private TextView toolbarTitleTv;
    private RecyclerView productCustomRecyclerView;
    private ArrayList<ProductModel> productModelArrayList;
    private ProductAdapter productAdapter;

    private RecyclerView menuPopUpCustomRecyclerView;
    private ArrayList<MallStallModel> mallStallModelArrayList;
    private MenuPopUpAdapter menuPopUpAdapter;

    private ImageView popupMenuImageView;
    private RelativeLayout popupMenuRelativeLayout;
    private TextView menuTextView;

    private TextView titleTv;
    private Button viewCartBtn;

    private String mallStallName;
    private Intent intent;

    private TextView plusCustomProductNameTv;
    private TextView plusCustomProductDescTv;
    private ImageView plusCustomProductImgRes;

    private String productName;
    private String productDescrip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeAsUpIndicator(R.color.colorWhite);

        titleTv = findViewById(R.id.plus_alertCustomLayout_titleTv);
        menuTextView = findViewById(R.id.menu_textView_customLayout_Tv);

        toolbarTitleTv = toolbar.findViewById(R.id.toolbar_title);
        intent = getIntent();
        mallStallName = intent.getStringExtra("KEY_TITLE");
        toolbarTitleTv.setText(mallStallName);
        menuTextView.setText(mallStallName);

        viewCartBtn = findViewById(R.id.bottomCustom_viewCartTv);
        viewCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });

        plusCustomProductNameTv = findViewById(R.id.plus_alertCustomLayout_titleTv);
        plusCustomProductDescTv = findViewById(R.id.mallStall_customLayout_stallTitle);
        plusCustomProductImgRes = findViewById(R.id.mallStall_customLayout_ImgView);

        popupMenuImageView = findViewById(R.id.menu_textView_customLayout_optionMenu);
        popupMenuRelativeLayout = findViewById(R.id.activity_product_popup_container_rl);

        popupMenuRelativeLayout.setVisibility(View.GONE);

        popupMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupMenuRelativeLayout.getVisibility() == View.GONE) {
                    popupMenuRelativeLayout.setVisibility(View.VISIBLE);
                } else  {
                    popupMenuRelativeLayout.setVisibility(View.GONE);
                }

            }
        });

        menuPopUpCustomRecyclerView = findViewById(R.id.activity_product_menuPopUp_recyclerView);
        productCustomRecyclerView = findViewById(R.id.activity_product_recyclerView);

        productModelArrayList = new ArrayList<>();
        productModelArrayList.add(new ProductModel("Peppy Paneer", "Peppy Paneer it's to good and with more cheesee and with more cheesee", "$60.00", R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita", "Margherita it's to cheese and with more cheesee and with more cheesee", "$50.00", R.drawable.lapinozpizza));
        productModelArrayList.add(new ProductModel("Peppy Paneer", "Peppy Paneer it's to good and with more cheesee and with more cheesee", "$60.00", R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita", "Margherita it's to cheese and with more cheesee and with more cheesee", "$50.00", R.drawable.lapinozpizza));
        productModelArrayList.add(new ProductModel("Peppy Paneer", "Peppy Paneer it's to good and with more cheesee and with more cheesee", "$60.00", R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita", "Margherita it's to cheese and with more cheesee and with more cheesee", "$50.00", R.drawable.lapinozpizza));
        productModelArrayList.add(new ProductModel("Peppy Paneer", "Peppy Paneer it's to good", "$60.00", R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita", "Margherita it's to cheese", "$50.00", R.drawable.lapinozpizza));

        productAdapter = new ProductAdapter(productModelArrayList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productCustomRecyclerView.setLayoutManager(layoutManager);
        productCustomRecyclerView.setAdapter(productAdapter);

        mallStallModelArrayList = new ArrayList<>();
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza"));
        mallStallModelArrayList.add(new MallStallModel("Sizzling"));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza"));
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza"));
        mallStallModelArrayList.add(new MallStallModel("Sizzling"));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza"));
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza"));
        mallStallModelArrayList.add(new MallStallModel("Sizzling"));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza"));

        menuPopUpAdapter = new MenuPopUpAdapter(mallStallModelArrayList, this);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        menuPopUpCustomRecyclerView.setLayoutManager(layoutManager1);
        menuPopUpCustomRecyclerView.setAdapter(menuPopUpAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void productOnItemClick(ProductModel productModel, View view, int position) {
        Toast.makeText(this, "" + productModel.getProductName(), Toast.LENGTH_SHORT).show();

        switch (view.getId()){
            case R.id.product_customLayout_ImgBtnAdd:
                /*Intent intent = new Intent(this, ProductActivity.class);
                intent.putExtra("KEY_PRODUCT_NAME", productModel.getProductName());
                intent.putExtra("KEY_PRODUCT_DESCRIP", productModel.getProductDescription());
                intent.putExtra("KEY_PRODUCT_IMGRES", productModel.getProductImgRes());
                startActivity(intent);

                intent = getIntent();
                String productName = intent.getStringExtra("KEY_PRODUCT_NAME");
                String productDescrip = intent.getStringExtra("KEY_PRODUCT_DESCRIP");*/


                final Dialog dialog = new Dialog(ProductActivity.this);
                dialog.setContentView(R.layout.plus_alert_custom_layout);
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                final ImageView closeImgView = dialog.findViewById(R.id.plus_alertCustomLayout_closeImgView);
                closeImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ProductActivity.this, "Dialog are closed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    public void mallStallOnItemClick(MallStallModel mallStallModel) {

    }

}
