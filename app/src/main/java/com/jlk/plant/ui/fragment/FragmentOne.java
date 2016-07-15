package com.jlk.plant.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jlk.plant.R;
import com.jlk.plant.adapter.ListCateAdapter;
import com.jlk.plant.adapter.NetworkImageHolderView;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.db.Cache.CacheUtils;
import com.jlk.plant.models.Banner;
import com.jlk.plant.models.Category;
import com.jlk.plant.models.requestmodels.GetCategoryListRequest;
import com.jlk.plant.models.returnmodels.GetBannerListReturn;
import com.jlk.plant.models.returnmodels.GetCategoryListReturn;
import com.jlk.plant.ui.IdentifyActivity;
import com.jlk.plant.ui.ListPlantActivity;
import com.jlk.plant.utils.L;
import com.jlk.plant.utils.OkHttpUtils;
import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 首页fragment
 *
 * @author jlk
 */
public class FragmentOne extends BaseFragment {
    private String tag = "FragmentOne";
    private TextView title;// 标题
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ArrayList<Category> data;
    private View headerView;
    ListCateAdapter mAdapter;
    private PullToLoadView mPullToLoadView;
    private boolean isLoading = false;
    private boolean isHasLoadedAll = false;

    private int page = 1;
    private int size = 10;

    private CacheUtils cache;

    TextView text_share, text_shop, text_identify, text_weather;

    @Override
    public void initData() {

        initCategoryData();
        initBannerData();

    }

    @Override
    public void initListeners() {
        mPullToLoadView.setPullCallback(new PullCallback() {
            @Override
            public void onLoadMore() {
                isLoading = true;
//                Toast.makeText(mContext, "拼命加载中...", Toast.LENGTH_SHORT).show();
                loadMoreCategoryData();
                L.i("onLoadMore");

            }

            @Override
            public void onRefresh() {
//                Toast.makeText(mContext, "拼命刷新中...", Toast.LENGTH_SHORT).show();
                initCategoryData();
                initBannerData();
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
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "该功能暂未开放,敬请期待!", Toast.LENGTH_SHORT).show();
            }
        };
        text_identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseFragmentActivity) getActivity()).startActivityAnim(null, IdentifyActivity.class);
            }
        });
        text_shop.setOnClickListener(listener);
        text_share.setOnClickListener(listener);
        text_weather.setOnClickListener(listener);
    }

    @Override
    public void initViews() {
        cache = new CacheUtils(mContext);

        headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_header_view, mRecyclerView, false);

        convenientBanner = (ConvenientBanner) headerView.findViewById(R.id.convenientBanner);

        text_share = (TextView) headerView.findViewById(R.id.text_share);
        text_shop = (TextView) headerView.findViewById(R.id.text_shop);
        text_identify = (TextView) headerView.findViewById(R.id.text_identify);
        text_weather = (TextView) headerView.findViewById(R.id.text_weather);

        mPullToLoadView = (PullToLoadView) mRootView.findViewById(R.id.pullToLoadView);
        //创建默认的线性LayoutManager
//        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview);
        mRecyclerView = mPullToLoadView.getRecyclerView();

        mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置item间隔
//        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 0));
        mPullToLoadView.isLoadMoreEnabled(false);

        mPullToLoadView.setColorSchemeResources(R.color.color_main);
//        mPullToLoadView.initLoad();
        //隐藏底部加载时进度条
//        ProgressBar progressBar = (ProgressBar) mPullToLoadView.findViewById(R.id.progressBar);
//        progressBar.setIndeterminateDrawable(null);

        //创建并设置Adapter
        mAdapter = new ListCateAdapter();
        mAdapter.setOnItemClickListener(new ListCateAdapter.OnItemClickListener<Category>() {

            @Override
            public void onItemClick(int position, Category data) {
                BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();

                Bundle bundle = new Bundle();
                bundle.putString("categoryId", data.getCategoryId());
                bundle.putString("categoryName", data.getCategoryName());

                activity.startActivityAnim(bundle, ListPlantActivity.class);
//                Toast.makeText(mContext, data.getCategoryName() + "被点击!", Toast.LENGTH_SHORT).show();

            }
        });
        mRecyclerView.setAdapter(mAdapter);

        loadBanner();

        loadCategory();


    }


    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_one;
    }

    /**
     * 设置banner图片
     *
     * @param list
     */
    private void setupBanner(List<Banner> list) {

        List<String> networkImages;

        if (list != null && list.size() > 0) {
            networkImages = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                networkImages.add(list.get(i).getImg());
            }

        } else {
            Toast.makeText(mContext, "数据获取失败。", Toast.LENGTH_SHORT).show();
            return;
        }
        //convenientBanner.setManualPageable(false);//设置不能手动影响
        //网络加载例子

        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
//                        Log.i(TAG, "num " + position + " was clicked!");
                    }
                });

    }


    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(3000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    /**
     * 进入界面加载banner
     */
    private void loadBanner() {
        List<Banner> list = cache.loadBannerFromDatabase();
        //数据库没有数据显示3张暂无图片
        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
            Banner item;
            for (int i = 0; i < 3; i++) {
                item = new Banner();
                item.setImg("drawable://" + R.mipmap.ic_default_not_found);
                list.add(item);
            }

        }
        setupBanner(list);
    }

    /**
     * 进入界面加载category
     */
    private void loadCategory() {
        data = (ArrayList<Category>) cache.loadCategoryFromDatabase();

        if (data == null || data.size() == 0) {
            data = new ArrayList<Category>();
        }

        mAdapter.addDatas(data);
        mAdapter.setHeaderView(headerView);

    }

    /**
     * 连接成功操作
     *
     * @param json
     */
    private void doOnPostSuccess(String json) {
        try {
            L.i("返回" + AppInterface.GETBANNERLIST + ":" + json);
            Gson gson = new Gson();
            GetBannerListReturn result = gson.fromJson(json, GetBannerListReturn.class);
            final List<Banner> list = result.getList();

            setupBanner(list);
            isLoading = false;
            mPullToLoadView.setComplete();

            new Thread() {
                public void run() {
                    //在子线程缓存数据
                    cache.CacheBannerToDatabase(list);
                }
            }.start();


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "接口出错，开发人员正在修复中。", Toast.LENGTH_SHORT).show();
            isLoading = false;
            mPullToLoadView.setComplete();
        }
    }


    /**
     * 连接失败操作
     *
     * @param e
     */
    private void doOnPostFail(IOException e) {

//

    }


    /**
     * 获取服务器中种类的数据
     */
    private void initCategoryData() {

        GetCategoryListRequest request = new GetCategoryListRequest(1, size);
        String json = new Gson().toJson(request);

        OkHttpUtils client = new OkHttpUtils(mContext, json, AppInterface.GETCATEGORYLIST);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(String json) {
                try {
                    L.i("返回" + AppInterface.GETCATEGORYLIST + ":" + json);
                    Gson gson = new Gson();
                    final GetCategoryListReturn result = gson.fromJson(json, GetCategoryListReturn.class);

                    data = (ArrayList<Category>) result.getList();

                    if (data == null || data.size() == 0) {
                        Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                        data = new ArrayList<Category>();
                    }

                    //创建并设置Adapter
                    mAdapter.removeAllData();
                    mAdapter.addDatas(data);

                    page = 1;
                    isHasLoadedAll = false;


                    isLoading = false;
                    mPullToLoadView.setComplete();
                    new Thread() {
                        public void run() {
                            //在子线程缓存数据
                            cache.CacheCategoryToDatabase(data);
                        }
                    }.start();


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "接口出错，开发人员正在修复中。", Toast.LENGTH_SHORT).show();
                    isLoading = false;
                    mPullToLoadView.setComplete();
                } finally {

                }
            }

            @Override
            public void onPostFailListener(IOException e) {
                e.printStackTrace();
                isLoading = false;
//                        loadCategory();
//                        Toast.makeText(mContext, "加载失败,请检查网络是否正常!", Toast.LENGTH_SHORT).show();
                mPullToLoadView.setComplete();

            }

            @Override
            public void onPrePostListener() {

            }
        });


        client.doPost();


    }

    /**
     * 获取服务器广告数据
     */
    public void initBannerData() {
        OkHttpUtils client = new OkHttpUtils(mContext, "", AppInterface.GETBANNERLIST);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(String response) {
                doOnPostSuccess(response);
            }

            @Override
            public void onPostFailListener(IOException e) {
                doOnPostFail(e);
            }

            @Override
            public void onPrePostListener() {
//                L.i("提交之前");
            }
        });

        client.doPost();

    }

    /**
     * 获取服务器中种类的数据
     */
    private void loadMoreCategoryData() {

        GetCategoryListRequest request = new GetCategoryListRequest(page + 1, size);
        String json = new Gson().toJson(request);

        OkHttpUtils client = new OkHttpUtils(mContext, json, AppInterface.GETCATEGORYLIST);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(String json) {
                try {
                    L.i("返回" + AppInterface.GETCATEGORYLIST + ":" + json);
                    Gson gson = new Gson();
                    final GetCategoryListReturn result = gson.fromJson(json, GetCategoryListReturn.class);


                    ArrayList<Category> newData = (ArrayList<Category>) result.getList();
                    if (newData == null || newData.size() == 0) {
//                                Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();

                    } else {
                        mAdapter.addDatas(newData);
                        page++;
                        L.i("page++");
                    }
                    if (newData.size() < size) {
                        isHasLoadedAll = true;

                    }
                    isLoading = false;
                    mPullToLoadView.setComplete();


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "接口出错，开发人员正在修复中。", Toast.LENGTH_SHORT).show();
                    isLoading = false;
                    mPullToLoadView.setComplete();
                }
            }

            @Override
            public void onPostFailListener(IOException e) {
                isLoading = false;
                mPullToLoadView.setComplete();

            }

            @Override
            public void onPrePostListener() {

            }
        });

        client.doPost();

    }

}
