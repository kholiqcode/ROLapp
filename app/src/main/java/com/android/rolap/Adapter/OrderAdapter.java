package com.android.rolap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rolap.Helper.Constant;
import com.android.rolap.Helper.Helper;
import com.android.rolap.R;
import com.android.rolap.Rest.Response.ResponseOrder;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderAdapter extends RecyclerView.Adapter {

    private List<ResponseOrder.Data> orderList;
    private Context mContext;
    private OrderAdapter.OnDetailsClick onDetailClick;
    private Helper helper;

    public OrderAdapter(Context mContext, List<ResponseOrder.Data> order){
        this.mContext = mContext;
        this.orderList = order;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_list, parent, false);
        return new OrderAdapter.OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        helper = new Helper(this.mContext);

        OrderAdapter.OrderViewHolder holder = (OrderAdapter.OrderViewHolder) viewHolder;
        holder.tvNamaTutor.setText(orderList.get(position).nama);
        holder.tvStatus.setText(helper.convertStatus(orderList.get(position).status));
        holder.tvTanggal.setText(orderList.get(position).tanggal);
        holder.cvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClick.onDetailClick(position);
            }
        });
        if(orderList.get(position).foto == ""){
            Glide.with(mContext).load(R.drawable.image_profil).into(holder.civTutor);
        }else{
            Glide.with(mContext).load(Constant.IMAGE_KATALOG+orderList.get(position).foto).into(holder.civTutor);
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setOnDetailClick(OrderAdapter.OnDetailsClick onDetailClick) {
        this.onDetailClick = onDetailClick;
    }

    public interface OnDetailsClick {
        void onDetailClick(int position);
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaTutor,tvStatus, tvTanggal;
        CardView cvOrder;
        CircleImageView civTutor;

        public OrderViewHolder(View v) {
            super(v);
            tvNamaTutor = itemView.findViewById(R.id.tvNamaTutor);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            cvOrder = itemView.findViewById(R.id.cvOrder);
            civTutor =  itemView.findViewById(R.id.civTutor);
        }
    }
}
