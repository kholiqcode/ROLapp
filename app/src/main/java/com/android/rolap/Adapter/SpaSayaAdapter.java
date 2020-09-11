package com.android.rolap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rolap.Helper.Constant;
import com.android.rolap.R;
import com.android.rolap.Rest.Response.ResponseSpaSaya;
import com.android.rolap.Rest.Response.ResponseTutorSaya;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpaSayaAdapter extends RecyclerView.Adapter {

    private List<ResponseSpaSaya.Data> spasayaList;
    private Context mContext;
    private SpaSayaAdapter.OnDetailsClick onDetailClick;
    private SpaSayaAdapter.OnDeleteClick onDeleteCLick;

    public SpaSayaAdapter(Context mContext, List<ResponseSpaSaya.Data> spasaya){
        this.mContext = mContext;
        this.spasayaList = spasaya;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myspa_list, parent, false);
        return new SpaSayaAdapter.SpaSayaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SpaSayaAdapter.SpaSayaViewHolder holder = (SpaSayaAdapter.SpaSayaViewHolder) viewHolder;
        holder.tvNama.setText(spasayaList.get(position).nama);
        holder.tvTransaksi.setText(spasayaList.get(position).total_trx+" Transaksi sukses");
        holder.cvTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClick.onDetailClick(position);
            }
        });
        holder.civDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteCLick.onDeleteClick(position);
            }
        });

        if(spasayaList.get(position).foto == ""){
            Glide.with(mContext).load(R.drawable.image_profil).into(holder.civTutor);
        }else{
            Glide.with(mContext).load(Constant.WEBSERVICE_IMAGE+spasayaList.get(position).foto).into(holder.civTutor);
        }
    }

    @Override
    public int getItemCount() {
        return spasayaList.size();
    }

    public void setOnDetailClick(SpaSayaAdapter.OnDetailsClick onDetailClick) {
        this.onDetailClick = onDetailClick;
    }

    public interface OnDetailsClick {
        void onDetailClick(int position);
    }

    public void setOnDeleteCLick(SpaSayaAdapter.OnDeleteClick OnDeleteClick) {
        this.onDeleteCLick = OnDeleteClick;
    }

    public interface OnDeleteClick {
        void onDeleteClick(int position);
    }

    private class SpaSayaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvTransaksi;
        CardView cvTutor;
        CircleImageView civTutor,civEdit,civDelete;

        public SpaSayaViewHolder(View v) {
            super(v);
            tvNama = itemView.findViewById(R.id.tvNamaSpa);
            tvTransaksi = itemView.findViewById(R.id.tvTotalTransaksi);
            cvTutor = itemView.findViewById(R.id.cvSpaSaya);
            civTutor =  itemView.findViewById(R.id.civSpa);
            civEdit =  itemView.findViewById(R.id.civEditSpa);
            civDelete =  itemView.findViewById(R.id.civDeleteSpa);
        }
    }
}
