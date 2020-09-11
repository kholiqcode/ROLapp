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
import com.android.rolap.Rest.Response.ResponseTutor;
import com.android.rolap.Rest.Response.ResponseTutorSaya;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TutorSayaAdapter extends RecyclerView.Adapter {

    private List<ResponseTutorSaya.Data> tutorsayaList;
    private Context mContext;
    private TutorSayaAdapter.OnDetailsClick onDetailClick;
    private TutorSayaAdapter.OnDeleteClick onDeleteCLick;

    public TutorSayaAdapter(Context mContext, List<ResponseTutorSaya.Data> tutorsaya){
        this.mContext = mContext;
        this.tutorsayaList = tutorsaya;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mytutor_list, parent, false);
        return new TutorSayaAdapter.TutorSayaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TutorSayaAdapter.TutorSayaViewHolder holder = (TutorSayaAdapter.TutorSayaViewHolder) viewHolder;
        holder.tvNama.setText(tutorsayaList.get(position).nama);
        holder.tvTransaksi.setText(tutorsayaList.get(position).total_trx+" Transaksi sukses");
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

        if(tutorsayaList.get(position).foto == ""){
            Glide.with(mContext).load(R.drawable.image_profil).into(holder.civTutor);
        }else{
            Glide.with(mContext).load(Constant.WEBSERVICE_IMAGE+tutorsayaList.get(position).foto).into(holder.civTutor);
        }
    }

    @Override
    public int getItemCount() {
        return tutorsayaList.size();
    }

    public void setOnDetailClick(TutorSayaAdapter.OnDetailsClick onDetailClick) {
        this.onDetailClick = onDetailClick;
    }

    public interface OnDetailsClick {
        void onDetailClick(int position);
    }

    public void setOnDeleteCLick(TutorSayaAdapter.OnDeleteClick OnDeleteClick) {
        this.onDeleteCLick = OnDeleteClick;
    }

    public interface OnDeleteClick {
        void onDeleteClick(int position);
    }

    private class TutorSayaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvTransaksi;
        CardView cvTutor;
        CircleImageView civTutor,civEdit,civDelete;

        public TutorSayaViewHolder(View v) {
            super(v);
            tvNama = itemView.findViewById(R.id.tvNamaTutor);
            tvTransaksi = itemView.findViewById(R.id.tvTotalTransaksi);
            cvTutor = itemView.findViewById(R.id.cvTutorSaya);
            civTutor =  itemView.findViewById(R.id.civTutor);
            civEdit =  itemView.findViewById(R.id.civEditTutor);
            civDelete =  itemView.findViewById(R.id.civDeleteItem);
        }
    }
}
