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
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TutorAdapter extends RecyclerView.Adapter {

    private List<ResponseTutor.Data> tutorList;
    private Context mContext;
    private TutorAdapter.OnDetailsClick onDetailClick;

    public TutorAdapter(Context mContext, List<ResponseTutor.Data> tutor){
        this.mContext = mContext;
        this.tutorList = tutor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutor_list, parent, false);
        return new TutorAdapter.TutorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TutorAdapter.TutorViewHolder holder = (TutorAdapter.TutorViewHolder) viewHolder;
        holder.tvNama.setText(tutorList.get(position).nama);
        holder.tvTotalTransaksi.setText(tutorList.get(position).total_trx+" Total Transaksi");
        if (!tutorList.get(position).rate_avg.equalsIgnoreCase("")) {
            holder.rbTutor.setRating(Float.parseFloat(tutorList.get(position).rate_avg));
        } else {
            holder.rbTutor.setRating(0);
        }
        holder.cvTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClick.onDetailClick(position);
            }
        });
        if(tutorList.get(position).foto == ""){
            Glide.with(mContext).load(R.drawable.image_profil).into(holder.civTutor);
        }else{
            Glide.with(mContext).load(Constant.WEBSERVICE_IMAGE+"tutor/"+tutorList.get(position).foto).into(holder.civTutor);
        }
    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }

    public void setOnDetailClick(TutorAdapter.OnDetailsClick onDetailClick) {
        this.onDetailClick = onDetailClick;
    }

    public interface OnDetailsClick {
        void onDetailClick(int position);
    }

    private class TutorViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvTotalTransaksi;
        RatingBar rbTutor;
        CardView cvTutor;
        CircleImageView civTutor;

        public TutorViewHolder(View v) {
            super(v);
            tvNama = itemView.findViewById(R.id.tvNamaTutor);
            rbTutor = itemView.findViewById(R.id.rbRating);
            tvTotalTransaksi = itemView.findViewById(R.id.tvTotalTransaksi);
            cvTutor = itemView.findViewById(R.id.cvTutor);
            civTutor =  itemView.findViewById(R.id.civTutor);
        }
    }
}
