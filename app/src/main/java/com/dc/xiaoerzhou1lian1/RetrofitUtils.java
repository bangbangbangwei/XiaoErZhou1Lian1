package com.dc.xiaoerzhou1lian1;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static RetrofitUtils Instance;
    private final Retrofit retrofit;

    private RetrofitUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static RetrofitUtils getInstance(){
        if (Instance == null){
            synchronized (RetrofitUtils.class){
                if (Instance == null){
                    Instance = new RetrofitUtils();
                }
            }
        }
        return Instance;
    }
    public void setProduct(String url, HashMap<String,String> params, final RetrofitUtilsCallBack retrofitUtilsCallBack){
        ProductApiService productApiService = retrofit.create(ProductApiService.class);
        Call<ProductBean> product = productApiService.product(url, params);
        product.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {
                ProductBean body = response.body();
                retrofitUtilsCallBack.success(body);
            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t) {
                retrofitUtilsCallBack.failure("网络错误");
            }
        });
    }
}
