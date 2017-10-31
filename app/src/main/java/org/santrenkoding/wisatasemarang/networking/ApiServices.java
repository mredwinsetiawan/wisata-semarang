package org.santrenkoding.wisatasemarang.networking;

import org.santrenkoding.wisatasemarang.ListWisataModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by edwin on 30/10/2017.
 */

public interface ApiServices {
    @GET("read_wisata.php")
    Call<ListWisataModel> ambilDataWisata();
}
