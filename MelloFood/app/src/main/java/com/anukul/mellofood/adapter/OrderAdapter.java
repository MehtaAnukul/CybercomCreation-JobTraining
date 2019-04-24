package com.anukul.mellofood.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anukul.mellofood.listener.OrderOnItemClickListener;
import com.anukul.mellofood.R;
import com.anukul.mellofood.model.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    private ArrayList<OrderModel> orderModelArrayList;
    private OrderOnItemClickListener orderOnItemClickListener;

    public OrderAdapter(ArrayList<OrderModel> orderModelArrayList, OrderOnItemClickListener orderOnItemClickListener) {
        this.orderModelArrayList = orderModelArrayList;
        this.orderOnItemClickListener = orderOnItemClickListener;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView orderProductName;
        TextView orderProductDescription;
        TextView orderProductTotalItem;
        TextView orderProductPrice;

        ImageView orderEditImgView;
        ImageView orderDeleteImgView;

        OrderModel orderModel;
        int position;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderProductName = itemView.findViewById(R.id.viewcartOrder_customlayout_productNameTv);
            orderProductDescription = itemView.findViewById(R.id.viewcartOrder_customlayout_descriptionTv);
            orderProductTotalItem = itemView.findViewById(R.id.viewcartOrder_customlayout_totalItemTv);
            orderProductPrice = itemView.findViewById(R.id.viewcartOrder_customlayout_priceTv);

            orderEditImgView = itemView.findViewById(R.id.viewcartOrder_customlayout_editImgView);
            orderDeleteImgView = itemView.findViewById(R.id.viewcartOrder_customlayout_deleteImgView);

            itemView.setOnClickListener(this);
            orderEditImgView.setOnClickListener(this);
            orderDeleteImgView.setOnClickListener(this);
        }

        public void setData(OrderModel data, int position) {
            this.orderModel = data;
            this.position = position;

            orderProductName.setText(orderModel.getOrderProductName());
            orderProductDescription.setText(orderModel.getOrderProductDescription());
            orderProductTotalItem.setText(orderModel.getOrderProductItem());
            orderProductPrice.setText(orderModel.getOrderProductPrice());
        }

        @Override
        public void onClick(View view) {
            if(orderOnItemClickListener != null){
                orderOnItemClickListener.orderProductOnItemClick(orderModel,view,position);
            }

        }
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewcart_order_custom_layout,viewGroup,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int position) {
        OrderModel orderModel = orderModelArrayList.get(position);

        orderViewHolder.setData(orderModel,position);
    }

    @Override
    public int getItemCount() {
        return orderModelArrayList.size();
    }


}
