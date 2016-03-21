package com.jlk.plant.ui.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jlk.plant.R;
import com.jlk.plant.adapter.ListArticleAdapter;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.models.Article;
import com.jlk.plant.models.requestmodels.GetArticleListRequest;
import com.jlk.plant.models.returnmodels.GetArticleListReturn;
import com.jlk.plant.utils.L;
import com.jlk.plant.utils.OkHttpUtils;
import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentArticleList extends BaseFragment {
    private String tag = "FragmentArticleList";
    private PullToLoadView mPullToLoadView;

    private int typeId = -1000;
    private String typeName;

    private ListArticleAdapter mAdapter;

    private Handler mHandler;

    private boolean isLoading = false;
    private boolean isRefreshing = false;
    private boolean isHasLoadedAll = false;

    private int page = 1;
    private int size = 8;

    @Override
    public void initData() {
        typeId = getArguments().getInt("typeId");
        typeName = getArguments().getString("typeName");

        //创建并设置Adapter
        mAdapter = new ListArticleAdapter();
        mAdapter.setOnItemClickListener(new ListArticleAdapter.OnItemClickListener<Article>() {

            @Override
            public void onItemClick(int position, Article data) {

            }

        });
        mPullToLoadView.getRecyclerView().setAdapter(mAdapter);
        mPullToLoadView.getRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
        mPullToLoadView.isLoadMoreEnabled(true);
        mPullToLoadView.initLoad();
//        loadMoreArticle();

    }

    private void loadMoreArticle() {
        GetArticleListRequest request;
        if ("全部".equals(typeName)) {
            //与服务器约定-1000获取全部数据
            request = new GetArticleListRequest(-1000, page, size);
        } else {
            request = new GetArticleListRequest(typeId, page, size);
        }

        String json = new Gson().toJson(request);

        OkHttpUtils client = new OkHttpUtils(mContext, json, AppInterface.GETARTICLTLIST);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(Call call, Response response) {
                try {
                    String json = response.body().string();
                    L.i("返回" + AppInterface.GETARTICLTLIST + ":" + json);
                    Gson gson = new Gson();
                    final GetArticleListReturn result = gson.fromJson(json, GetArticleListReturn.class);


                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Article> newData = (ArrayList<Article>) result.getList();
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
                                L.i("page-----" + page);

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
                    mHandler.post(new Runnable() {
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
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        isLoading = false;
                        isRefreshing = false;
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

    @Override
    public void initListeners() {
        mPullToLoadView.setPullCallback(new PullCallback() {
            @Override
            public void onLoadMore() {
                isLoading = true;
//                Toast.makeText(mContext, "拼命加载中...", Toast.LENGTH_SHORT).show();
                loadMoreArticle();
                L.i("onLoadMore");

            }

            @Override
            public void onRefresh() {
                page = 1;
                isRefreshing = true;
                loadMoreArticle();
                isHasLoadedAll = false;
                L.i("onRefresh");
            }

            @Override
            public boolean isLoading() {
                if (isLoading) {
                    L.i("isLoading true");
                } else {

                    L.i("isLoading false");
                }
                return isLoading;
            }

            @Override
            public boolean hasLoadedAllItems() {
                if (isHasLoadedAll) {
                    L.i("isHasLoadedAll true");
                } else {

                    L.i("isHasLoadedAll false");
                }
                return isHasLoadedAll;
            }
        });
    }

    @Override
    public void initViews() {

        mHandler = new Handler();

        mPullToLoadView = (PullToLoadView) mRootView.findViewById(R.id.pullToLoadView);

        mPullToLoadView.setColorSchemeResources(R.color.color_main);
    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_list;
    }

}
