package com.yulia.informasikehamilan.ADAPTOR;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yulia.informasikehamilan.MODEL.Model_notif;
import com.yulia.informasikehamilan.MODEL.Model_ringan;
import com.yulia.informasikehamilan.R;

import java.util.ArrayList;

public class Adapter_notif extends RecyclerView.Adapter<Adapter_notif.myViewHolder>{
    ArrayList<Model_notif> notifArrayList;

    public Adapter_notif(ArrayList<Model_notif> notifArrayList) {
        this.notifArrayList = notifArrayList;
    }

    @NonNull
    @Override
    public Adapter_notif.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.format_notif,parent,false);
        return new Adapter_notif.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_notif.myViewHolder holder, int position) {
        holder.enotif.setText(notifArrayList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return notifArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView enotif;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            enotif=itemView.findViewById(R.id.textnotif);
        }
    }
}
