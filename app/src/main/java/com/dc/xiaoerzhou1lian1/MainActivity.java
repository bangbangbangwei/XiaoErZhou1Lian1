package com.dc.xiaoerzhou1lian1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Cantract.IProductView,XRecyclerView.LoadingListener {
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.xre)
    XRecyclerView xre;
    int page = 1;
    private ArrayList<ProductBean.Result> result;
    private ProductPresenter productPresenter;
    private HashMap<String, String> params;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        result = new ArrayList<>();
        productPresenter = new ProductPresenter(this);
        params = new HashMap<>();
        params.put("keyword","手机");
        params.put("page",1+"");
        params.put("count",10+"");
        productPresenter.setProductList(Api.PRODUCT_API,params);
    }

    private void initView() {
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        xre.setLayoutManager(new GridLayoutManager(this,2));
        productAdapter = new ProductAdapter(this);
        xre.setLoadingListener(this);
        xre.setAdapter(productAdapter);
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void success(Object o) {
        ProductBean productBean = (ProductBean) o;
        result = productBean.result;
        if (page == 1){
            productAdapter.setList(result);
        }else{
            productAdapter.addList(result);
        }
        page++;
    }

    @Override
    public void onRefresh() {
        params.put("page",1+"");
        productPresenter.setProductList(Api.PRODUCT_API,params);
        xre.loadMoreComplete();
    }

    @Override
    public void onLoadMore() {
        params.put("page",page+"");
        productPresenter.setProductList(Api.PRODUCT_API,params);
        xre.loadMoreComplete();
    }

    public void Sreach(View view) {
    }
}
