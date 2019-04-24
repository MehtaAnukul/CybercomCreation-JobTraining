package com.anukul.mellofood.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.anukul.mellofood.listener.ProductOnItemClickListener;
import com.anukul.mellofood.R;
import com.anukul.mellofood.model.ProductModel;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<ProductModel> productModelArrayList;
    private ProductOnItemClickListener productOnItemClickListener;

    public ProductAdapter(ArrayList<ProductModel> productModelArrayList, ProductOnItemClickListener productOnItemClickListener) {
        this.productModelArrayList = productModelArrayList;
        this.productOnItemClickListener = productOnItemClickListener;
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView productName;
        TextView productDescription;
        TextView productPrice;
        ImageView productImgRes;
        ImageButton productAddImgBtn;

        ProductModel productModel;
        int position;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_customLayout_productNameTv);
            productDescription = itemView.findViewById(R.id.product_customLayout_productDescriptionTv);
            productPrice = itemView.findViewById(R.id.product_customLayout_productPriceTv);
            productImgRes = itemView.findViewById(R.id.product_customLayout_productImgView);
            productAddImgBtn = itemView.findViewById(R.id.product_customLayout_ImgBtnAdd);

            itemView.setOnClickListener(this);
            productAddImgBtn.setOnClickListener(this);
        }

        public void setData(ProductModel data, int position) {
            this.productModel = data;
            this.position = position;

            productName.setText(productModel.getProductName());
            productDescription.setText(productModel.getProductDescription());
            productPrice.setText(productModel.getProductPrice());
            productImgRes.setImageResource(productModel.getProductImgRes());
        }
        @Override
        public void onClick(View view) {
            if(productOnItemClickListener != null){
                productOnItemClickListener.productOnItemClick(productModel,view,position);
            }
        }
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_custom_layout,viewGroup,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
        ProductModel productModel = productModelArrayList.get(position);

        productViewHolder.setData(productModel,position);
    }

    @Override
    public int getItemCount() {
        return productModelArrayList.size();
    }
}
