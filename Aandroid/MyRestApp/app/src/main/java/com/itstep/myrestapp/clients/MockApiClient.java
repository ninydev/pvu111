package com.itstep.myrestapp.clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MockApiClient {

    private static final String BASE_URL = "https://6640ac2da7500fcf1a9e6234.mockapi.io";

    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
