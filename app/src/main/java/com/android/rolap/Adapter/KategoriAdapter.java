package com.android.rolap.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rolap.Helper.Constant;
import com.android.rolap.R;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class KategoriAdapter extends RecyclerView.Adapter {

    private List<ResponseKategori.Data> kategoriList;
    private Context mContext;
    private OnDetailsClick onDetailClick;

    public KategoriAdapter(Context mContext, List<ResponseKategori.Data> kategori){
        this.mContext = mContext;
        this.kategoriList = kategori;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori_list, parent, false);
        return new KategoriViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        KategoriAdapter.KategoriViewHolder holder = (KategoriAdapter.KategoriViewHolder) viewHolder;
        holder.tvNama.setText(kategoriList.get(position).nama);
        holder.tvTotalTutor.setText(kategoriList.get(position).total_tutor+" Tutor Aktif");
        holder.cvKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClick.onDetailClick(position);
            }
        });
        if(kategoriList.get(position).foto == null){
            Glide.with(mContext).load(R.drawable.image_profil).into(holder.civKategori);
        }else{
            Glide.with(mContext).load(Constant.WEBSERVICE_IMAGE+kategoriList.get(position).foto).into(holder.civKategori);
        }
    }

    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    public void setOnDetailClick(OnDetailsClick onDetailClick) {
        this.onDetailClick = onDetailClick;
    }

    public interface OnDetailsClick {
        void onDetailClick(int position);
    }

    private class KategoriViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvTotalTutor;
        CardView cvKategori;
        CircleImageView civKategori;

        public KategoriViewHolder(View v) {
            super(v);
            tvNama = itemView.findViewById(R.id.tvNamaKategori);
            tvTotalTutor = itemView.findViewById(R.id.tvTotalTutor);
            cvKategori = itemView.findViewById(R.id.cvKategori);
            civKategori =  itemView.findViewById(R.id.civKategori);
        }
    }
}
