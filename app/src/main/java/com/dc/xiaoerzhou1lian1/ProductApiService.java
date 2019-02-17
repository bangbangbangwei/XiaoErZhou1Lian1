package com.dc.xiaoerzhou1lian1;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ProductApiService {
    @GET
    Call<ProductBean> product(@Url String api, @QueryMap HashMap<String,String> params);
}
