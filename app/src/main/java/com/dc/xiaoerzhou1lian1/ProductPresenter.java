package com.dc.xiaoerzhou1lian1;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ProductPresenter {
    public Cantract.IProductView iProductView;
    public ProductModel productModel;
    public WeakReference<Cantract.IProductView> weakReference;

    public ProductPresenter(Cantract.IProductView iProductView) {
        this.weakReference = new WeakReference<>(iProductView);
        this.productModel = new ProductModel();
        this.iProductView = weakReference.get();
    }
    public void setProductList(String api, HashMap<String,String> params){
        productModel.getProductList(api, params, new RetrofitUtilsCallBack() {
            @Override
            public void failure(String msg) {
                iProductView.failure(msg);
            }

            @Override
            public void success(Object o) {
                iProductView.success(o);
            }
        });
    }
    public void dettach(){
        if (weakReference != null){
            weakReference.clear();
            weakReference = null;
            iProductView = null;
        }
    }
}
