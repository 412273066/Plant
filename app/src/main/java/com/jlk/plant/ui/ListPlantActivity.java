package com.jlk.plant.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jlk.plant.R;
import com.jlk.plant.adapter.ListCateAdapter;
import com.jlk.plant.adapter.ListPlantAdapter;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.models.Plant;
import com.jlk.plant.models.requestmodels.GetPlantListRequest;
import com.jlk.plant.models.returnmodels.GetPlantListReturn;
import com.jlk.plant.utils.L;
import com.jlk.plant.utils.OkHttpUtils;
import com.shamanland.fab.FloatingActionButton;
import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


public class ListPlantActivity extends BaseFragmentActivity {

    private final String tag = "ListPlantActivity";
    private FloatingActionButton fab;
    private int page = 1;
    private int size = 8;
    ListPlantAdapter mAdapter;
    private PullToLoadView mPullToLoadView;
    private RecyclerView mRecyclerView;
    private boolean isLoading = false;
    private boolean isRefreshing = false;
    private boolean isHasLoadedAll = false;
    private int categoryId;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_plant_list);
    }

    @Override
    public void initViews() {

        findViewById(R.id.back).setVisibility(View.VISIBLE);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //下滑隐藏 上滑显示
//        listView.setOnTouchListener(new ShowHideOnScroll(fab));
        mPullToLoadView = (PullToLoadView) findViewById(R.id.pullToLoadView);
        //创建默认的线性LayoutManager
//        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview);
        mRecyclerView = mPullToLoadView.getRecyclerView();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置item间隔
//        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 0));
        mPullToLoadView.isLoadMoreEnabled(true);
        //设置加载圈颜色
        mPullToLoadView.setColorSchemeResources(R.color.color_main);

//        mPullToLoadView.initLoad();
        //隐藏底部加载时进度条
//        ProgressBar progressBar = (ProgressBar) mPullToLoadView.findViewById(R.id.progressBar);
//        progressBar.setIndeterminateDrawable(null);
    }

    @Override
    public void initListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityAnim(null, SearchActivity.class);
            }
        });
        mPullToLoadView.setPullCallback(new PullCallback() {
            @Override
            public void onLoadMore() {
                isLoading = true;
//                Toast.makeText(mContext, "拼命加载中...", Toast.LENGTH_SHORT).show();
                L.i("onLoadMore");
                loadMorePlant(categoryId);
            }

            @Override
            public void onRefresh() {
//                Toast.makeText(mContext, "拼命刷新中...", Toast.LENGTH_SHORT).show();
                page = 1;
                isRefreshing = true;
                loadMorePlant(categoryId);
                isHasLoadedAll = false;
                L.i("onRefresh");
            }

            @Override
            public boolean isLoading() {
//                if (isLoading) {
//                    L.i("isLoading true");
//                } else {
//
//                    L.i("isLoading false");
//                }
                return isLoading;
            }

            @Override
            public boolean hasLoadedAllItems() {
//                if (isHasLoadedAll) {
//                    L.i("isHasLoadedAll true");
//                } else {
//
//                    L.i("isHasLoadedAll false");
//                }
                return isHasLoadedAll;
            }
        });

    }

    /**
     *
     */
    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();

        try {
            String id = bundle.getString("categoryId");
            categoryId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            categoryId = 1;
        }


        L.i("categoryId:" + categoryId);
        //创建并设置Adapter
        mAdapter = new ListPlantAdapter();
        mAdapter.setOnItemClickListener(new ListCateAdapter.OnItemClickListener<Plant>() {

            @Override
            public void onItemClick(int position, Plant data) {

                Bundle mBundle = new Bundle();
                mBundle.putString("img", data.getImg());
                mBundle.putString("feature", data.getPlantFeature());
                mBundle.putString("habit", data.getPlantHabit());
                mBundle.putString("info", data.getPlantInfo());
                mBundle.putString("name", data.getPlantName());
                mBundle.putString("use", data.getPlantUse());

                startActivityAnim(mBundle, DetailPlantActivity.class);

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        loadMorePlant(categoryId);
    }

    @Override
    public String getTitleName() {
        Bundle bundle = getIntent().getExtras();

        String title = bundle.getString("categoryName");

        return title;
    }

    private void loadMorePlant(int categoryId) {
        GetPlantListRequest request = new GetPlantListRequest(categoryId, page, size);
        String json = new Gson().toJson(request);

        OkHttpUtils client = new OkHttpUtils(mContext, json, AppInterface.GETPLANTLIST);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(Call call, Response response) {
                try {
                    String json = response.body().string();
                    L.i("返回" + AppInterface.GETPLANTLIST + ":" + json);
                    Gson gson = new Gson();
                    final GetPlantListReturn result = gson.fromJson(json, GetPlantListReturn.class);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Plant> newData = (ArrayList<Plant>) result.getList();
                            if (newData == null || newData.size() == 0) {
                                Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                                isHasLoadedAll = true;
                            } else {

                                if (isRefreshing) {
                                    L.i("刷新时清空adapter!");
                                    mAdapter.removeAllData();
                                    isRefreshing = false;
                                }

                                mAdapter.addDatas(newData);
                                page++;
                                L.i("page++");

                            }
                            if (newData.size() < size) {
                                isHasLoadedAll = true;
                            }
                            isLoading = false;
                            mPullToLoadView.setComplete();
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "接口出错，开发人员正在修复中。", Toast.LENGTH_SHORT).show();
                            isLoading = false;
                            isRefreshing = false;
                            mPullToLoadView.setComplete();
                        }
                    });
                } finally {

                }
            }

            @Override
            public void onPostFailListener(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isLoading = false;
                        mPullToLoadView.setComplete();
                    }
                });
            }

            @Override
            public void onPrePostListener() {

            }
        });


        client.doPost();


    }


}
