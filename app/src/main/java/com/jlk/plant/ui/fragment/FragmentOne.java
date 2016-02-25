package com.jlk.plant.ui.fragment;

import android.content.Intent;
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
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.custom.view.SpacesItemDecoration;
import com.jlk.plant.db.dao.BannerDao;
import com.jlk.plant.models.Banner;
import com.jlk.plant.models.Category;
import com.jlk.plant.models.returnmodels.GetBannerListReturn;
import com.jlk.plant.ui.ListPlantActivity;
import com.jlk.plant.utils.L;
import com.jlk.plant.utils.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentOne extends BaseFragment {
    private String tag = "FragmentOne";
    private TextView title;// 标题
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private String[] images;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ArrayList<Category> data;
    private View headerView;

    @Override
    public void initData() {
        data = new ArrayList<>();
        Category item1 = new Category("1", "观花植物", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3903672296,3890938056&fm=5");
        Category item2 = new Category("1", "观草植物", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=4056768107,349449491&fm=5");
        Category item3 = new Category("1", "室内植物", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1507857947,1081769355&fm=58");
        Category item4 = new Category("1", "草本植物", "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1015996287,2371183842&fm=58");
        Category item5 = new Category("1", "木本植物", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2516940836,1070676706&fm=58");
        Category item6 = new Category("1", "多肉植物", "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2875812309,3386562784&fm=58");
        Category item7 = new Category("1", "食肉植物", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2730420098,2001633972&fm=58");
        Category item8 = new Category("1", "水生植物", "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3361260144,1666757931&fm=58");
        data.add(item1);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        data.add(item5);
        data.add(item6);
        data.add(item7);
        data.add(item8);

        //创建并设置Adapter
        ListCateAdapter mAdapter = new ListCateAdapter();
        mAdapter.addDatas(data);
        mAdapter.setHeaderView(headerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ListCateAdapter.OnItemClickListener<Category>() {

            @Override
            public void onItemClick(int position, Category data) {
                Intent intent = new Intent(mContext, ListPlantActivity.class);
                mContext.startActivity(intent);
//                Toast.makeText(mContext, data.getCategoryName() + "被点击!", Toast.LENGTH_SHORT).show();
            }
        });

        OkHttpUtils client = new OkHttpUtils("", AppInterface.GETBANNERLIST);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(Call call, Response response) {
                doOnPostSuccess(call, response);
            }

            @Override
            public void onPostFailListener(Call call, IOException e) {
                doOnPostFail(call, e);
            }

            @Override
            public void onPrePostListener() {
//                L.i("提交之前");
            }
        });

        client.doPost();


    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {


        headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_header_view, mRecyclerView, false);

        convenientBanner = (ConvenientBanner) headerView.findViewById(R.id.convenientBanner);

        //创建默认的线性LayoutManager
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview);
        mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置item间隔
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 0));

        loadBanner();
    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_one;
    }

    /**
     * 设置图片
     *
     * @param list
     */
    private void initBanner(List<Banner> list) {

        if (list != null && list.size() > 0) {
            images = new String[list.size()];

            for (int i = 0; i < images.length; i++) {
                images[i] = list.get(i).getImg();
            }

        } else {
            Toast.makeText(mContext, "数据获取失败。", Toast.LENGTH_SHORT).show();
            return;
        }


//      convenientBanner.setManualPageable(false);//设置不能手动影响

        //网络加载例子
        List<String> networkImages = Arrays.asList(images);
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
        List<Banner> list = loadFromDatabase();
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
        initBanner(list);
    }

    /**
     * 连接成功操作
     *
     * @param call
     * @param response
     */
    private void doOnPostSuccess(Call call, Response response) {
        try {
            String json = response.body().string();
            Gson gson = new Gson();
            GetBannerListReturn result = gson.fromJson(json, GetBannerListReturn.class);
            final List<Banner> list = result.getList();

            CacheToDatabase(list);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initBanner(list);
                }
            });

            L.i("返回json->" + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 连接失败操作
     *
     * @param call
     * @param e
     */
    private void doOnPostFail(Call call, IOException e) {

//        final List<Banner> list = loadFromDatabase();
//
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                initBanner(list);
//            }
//        });
        e.printStackTrace();
        L.i("连接失败");

    }

    /**
     * 缓存到数据库
     *
     * @param list
     */
    private void CacheToDatabase(List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        BannerDao dao = new BannerDao(mContext);
        if (dao.delAll()) {
            L.i("清除成功");
        }
        if (dao.addList(list)) {
            L.i("添加成功");
        }
    }

    /**
     * 从数据库加载
     *
     * @return
     */
    private List loadFromDatabase() {

        BannerDao dao = new BannerDao(mContext);

        return dao.queryAll();
    }

}
