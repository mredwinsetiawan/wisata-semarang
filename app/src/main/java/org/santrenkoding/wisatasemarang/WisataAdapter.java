package org.santrenkoding.wisatasemarang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by edwin on 30/10/2017.
 */

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.MyViewHolder> {

    private ArrayList<WisataModel> listData;
    private Context context;


    public WisataAdapter(ArrayList<WisataModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    //Menghubungkan dengan itemnya
    @Override
    public WisataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wisata_item_list, parent, false);
        return new MyViewHolder(itemView);
    }

    //buat set text
    @Override
    public void onBindViewHolder(WisataAdapter.MyViewHolder holder, int position) {
        holder.tvNamaWisata.setText(listData.get(position).getNamaWisata());
        holder.tvAlamatWisata.setText(listData.get(position).getAlamatWisata());
        Glide.with(context)
                .load("http://52.187.117.60/wisata_semarang/img/wisata/"+listData.get(position).getGambarWisata())
                .placeholder(R.drawable.no_image_found)
                .error(R.drawable.no_image_found)
                .into(holder.ivGambarWisata);

        //untuk kirim data
        final Bundle datakiriman = new Bundle();
        datakiriman.putString(Konstanta.DATA_NAMA,listData.get(position).getNamaWisata());
        datakiriman.putString(Konstanta.DATA_ALAMAT,listData.get(position).getAlamatWisata());
        datakiriman.putString(Konstanta.DATA_GAMBAR,listData.get(position).getGambarWisata());
        datakiriman.putString(Konstanta.DATA_DESKRIPSI,listData.get(position).getDeksripsiWisata());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //pindah halaman
                    Intent pindah = new Intent(context, DetailWisataActivity.class);
                    pindah.putExtras(datakiriman);
                    context.startActivity(pindah);
                }
            });
    }

    // buat ngitung list
    @Override
    public int getItemCount() {
        return listData.size();
    }

    // inialisasi item item /Widget
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarWisata;
        TextView tvNamaWisata, tvAlamatWisata;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivGambarWisata = (ImageView) itemView.findViewById(R.id.iv_item_gambar);
            tvAlamatWisata = (TextView) itemView.findViewById(R.id.tv_item_alamat);
            tvNamaWisata = (TextView) itemView.findViewById(R.id.tv_item_nama);
        }
    }
}
