package com.dc.xiaoerzhou1lian1;

import java.util.HashMap;

public interface Cantract {
    interface IProductModel{
        void getProductList(String url, HashMap<String,String> params,RetrofitUtilsCallBack retrofitUtilsCallBack);

    }
    interface IProductView{
        void failure(String msg);
        void success(Object o);
    }
}
