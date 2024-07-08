package com.yulia.informasikehamilan.ADAPTOR;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yulia.informasikehamilan.MODEL.Model_berat;
import com.yulia.informasikehamilan.R;

import java.util.ArrayList;

public class Adapter_berat extends RecyclerView.Adapter<Adapter_berat.myViewHolder> {

    ArrayList<Model_berat> beratArrayList;

    public Adapter_berat(ArrayList<Model_berat> beratArrayList) {
        this.beratArrayList = beratArrayList;
    }

    //menggabungkan / menimpa main activity berat dengan format tampilan berat
    @NonNull
    @Override
    public Adapter_berat.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.format_berat,parent,false);
        return new Adapter_berat.myViewHolder(view);
    }

    //mengambil, menempatkan dan menampilkan data
    @Override
    public void onBindViewHolder(@NonNull Adapter_berat.myViewHolder holder, int position) {
        holder.ejudul.setText(beratArrayList.get(position).getJudul());
        holder.eketerangan.setText(beratArrayList.get(position).getKeterangan());
        holder.egejala.setText(beratArrayList.get(position).getGejala());
        holder.epenyebab.setText(beratArrayList.get(position).getPenyebab());
        holder.epenanganan.setText(beratArrayList.get(position).getPenanganan());
    }
//jumlah data yang bergulir didalam recyclerview
    @Override
    public int getItemCount() {
        return beratArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView ejudul, eketerangan, egejala, epenyebab, epenanganan;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ejudul=itemView.findViewById(R.id.judulberat);
            eketerangan=itemView.findViewById(R.id.keteranganberat);
            egejala=itemView.findViewById(R.id.gejalaberat);
            epenyebab=itemView.findViewById(R.id.penyebabberat);
            epenanganan=itemView.findViewById(R.id.penangananberat);
        }
    }
}
