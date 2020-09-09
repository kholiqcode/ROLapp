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
import com.android.rolap.Rest.Response.ResponseJadwal;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class JadwalAdapter extends RecyclerView.Adapter {

    private List<ResponseJadwal.Data> jadwalList;
    private Context mContext;
    private JadwalAdapter.OnDetailsClick onDetailClick;
    private Helper helper;

    public JadwalAdapter(Context mContext, List<ResponseJadwal.Data> tutor){
        this.mContext = mContext;
        this.jadwalList = tutor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_list, parent, false);
        return new JadwalAdapter.JadwalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        helper = new Helper(this.mContext);

        JadwalAdapter.JadwalViewHolder holder = (JadwalAdapter.JadwalViewHolder) viewHolder;
        holder.tvNamaUser.setText(jadwalList.get(position).nama_user);
        holder.tvTanggal.setText(jadwalList.get(position).tanggal);
        holder.tvStatus.setText(Helper.convertStatus(jadwalList.get(position).status));

        holder.cvJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClick.onDetailClick(position);
            }
        });
        if(jadwalList.get(position).foto == ""){
            Glide.with(mContext).load(R.drawable.image_profil).into(holder.civJadwal);
        }else{
            Glide.with(mContext).load(Constant.WEBSERVICE_IMAGE+jadwalList.get(position).foto).into(holder.civJadwal);
        }
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    public void setOnDetailClick(JadwalAdapter.OnDetailsClick onDetailClick) {
        this.onDetailClick = onDetailClick;
    }

    public interface OnDetailsClick {
        void onDetailClick(int position);
    }

    private class JadwalViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaUser, tvTanggal,tvStatus;
        CardView cvJadwal;
        CircleImageView civJadwal;

        public JadwalViewHolder(View v) {
            super(v);
            tvNamaUser = itemView.findViewById(R.id.tvNamaUser);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            cvJadwal = itemView.findViewById(R.id.cvJadwal);
            civJadwal =  itemView.findViewById(R.id.civJadwal);
        }
    }
}
