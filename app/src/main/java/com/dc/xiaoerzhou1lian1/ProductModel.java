package com.dc.xiaoerzhou1lian1;

import java.util.HashMap;

public class ProductModel implements Cantract.IProductModel {
    @Override
    public void getProductList(String url, HashMap<String, String> params, RetrofitUtilsCallBack retrofitUtilsCallBack) {
        RetrofitUtils.getInstance().setProduct(url,params,retrofitUtilsCallBack);
    }
}
