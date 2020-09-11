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
import com.android.rolap.R;
import com.android.rolap.Rest.Response.ResponseSpa;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpaAdapter extends RecyclerView.Adapter {

    private List<ResponseSpa.Data> spaList;
    private Context mContext;
    private SpaAdapter.OnDetailsClick onDetailClick;

    public SpaAdapter(Context mContext, List<ResponseSpa.Data> spa){
        this.mContext = mContext;
        this.spaList = spa;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spa_list, parent, false);
        return new SpaAdapter.SpaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SpaAdapter.SpaViewHolder holder = (SpaAdapter.SpaViewHolder) viewHolder;
        holder.tvNama.setText(spaList.get(position).nama);
        holder.tvTotalTransaksi.setText(spaList.get(position).total_trx+" Total Transaksi");
        if (!spaList.get(position).rate_avg.equalsIgnoreCase("")) {
            holder.rbSpa.setRating(Float.parseFloat(spaList.get(position).rate_avg));
        } else {
            holder.rbSpa.setRating(0);
        }
        holder.cvSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClick.onDetailClick(position);
            }
        });
        if(spaList.get(position).foto == ""){
            Glide.with(mContext).load(R.drawable.image_profil).into(holder.civSpa);
        }else{
            Glide.with(mContext).load(Constant.WEBSERVICE_IMAGE+spaList.get(position).foto).into(holder.civSpa);
        }
    }

    @Override
    public int getItemCount() {
        return spaList.size();
    }

    public void setOnDetailClick(SpaAdapter.OnDetailsClick onDetailClick) {
        this.onDetailClick = onDetailClick;
    }

    public interface OnDetailsClick {
        void onDetailClick(int position);
    }

    private class SpaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvTotalTransaksi;
        RatingBar rbSpa;
        CardView cvSpa;
        CircleImageView civSpa;

        public SpaViewHolder(View v) {
            super(v);
            tvNama = itemView.findViewById(R.id.tvNamaSpa);
            rbSpa = itemView.findViewById(R.id.rbRating);
            tvTotalTransaksi = itemView.findViewById(R.id.tvTotalTransaksi);
            cvSpa = itemView.findViewById(R.id.cvSpa);
            civSpa =  itemView.findViewById(R.id.civSpa);
        }
    }
}
