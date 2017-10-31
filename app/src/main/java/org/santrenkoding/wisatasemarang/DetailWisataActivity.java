package org.santrenkoding.wisatasemarang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailWisataActivity extends AppCompatActivity {
    private static final String TAG = "DetailWisataActivity";

    String dataNama, dataAlamat, dataDeskripsi, dataGambar;
    Boolean isFavorit;
    SharedPreferences pref;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // terima data
        dataNama = getIntent().getExtras().getString(Konstanta.DATA_NAMA);
        dataAlamat = getIntent().getExtras().getString(Konstanta.DATA_ALAMAT);
        dataDeskripsi = getIntent().getExtras().getString(Konstanta.DATA_DESKRIPSI);
        dataGambar = getIntent().getExtras().getString(Konstanta.DATA_GAMBAR);


        //untuk menampilkan di log
        Log.d(TAG, "onCreate: "+ dataNama+dataGambar+dataDeskripsi+dataAlamat);

        pref = getSharedPreferences(Konstanta.SETTING, MODE_PRIVATE);
        isFavorit = pref.getBoolean(Konstanta.TAG_PREF+dataNama, false);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        cekFavorit(isFavorit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isFavorit){
                    //simpan ke favorit
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Konstanta.TAG_PREF+dataNama,false);
                    editor.commit();
                    isFavorit = false;

                }else {
                    //simpan ke favorit
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Konstanta.TAG_PREF+dataNama,true);
                    editor.commit();
                    isFavorit = true;

                }
                cekFavorit(isFavorit);

                //kenapa hayooo?
                //fab.setImageResource(R.drawable.ic_action_isfavorit);

            }
        });
        



        TextView tvAlamat = (TextView) findViewById(R.id.tv_detail_alamat);
        TextView tvDeskripsi = (TextView) findViewById(R.id.tv_detail_deskripsi);
        ImageView ivGambar = (ImageView) findViewById(R.id.iv_detail_gambar);

        tvAlamat.setText(dataAlamat);
        tvDeskripsi.setText(dataDeskripsi);
        Glide.with(DetailWisataActivity.this)
                .load("http://52.187.117.60/wisata_semarang/img/wisata/"+dataGambar)
                .placeholder(R.drawable.no_image_found)
                .error(R.drawable.no_image_found)
                .into(ivGambar);
    }

    private void cekFavorit(Boolean isFavorit) {
        if (isFavorit){
            fab.setImageResource(R.drawable.ic_action_isfavorit);
        }else{
            fab.setImageResource(R.drawable.ic_action_notfavorit);
        }
    }
}
