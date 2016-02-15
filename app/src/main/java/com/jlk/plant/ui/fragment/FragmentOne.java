package com.jlk.plant.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jlk.plant.R;
import com.jlk.plant.adapter.ListCateAdapter;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.custom.view.SpacesItemDecoration;
import com.jlk.plant.models.Category;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentOne extends BaseFragment {
    private String TAG = "FragmentOne";
    private TextView title;// 标题
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private List<String> networkImages;
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ArrayList<Category> data;

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

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {
        convenientBanner = (ConvenientBanner) mRootView.findViewById(R.id.convenientBanner);
        init();
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview);
        //创建默认的线性LayoutManager
        mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置item间隔
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(0,-3,-3,-3));
        //创建并设置Adapter
        ListCateAdapter mAdapter = new ListCateAdapter();
        mAdapter.addDatas(data);
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_header_view, mRecyclerView, false);
        mAdapter.setHeaderView(headerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ListCateAdapter.OnItemClickListener<Category>() {

            @Override
            public void onItemClick(int position, Category data) {
                Toast.makeText(mContext, data.getCategoryName() + "被点击!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_one;
    }

    private void init() {
        initImageLoader();
//      convenientBanner.setManualPageable(false);//设置不能手动影响

        //网络加载例子
        networkImages = Arrays.asList(images);
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
                        Log.i(TAG, "num " + position + " was clicked!");
                    }
                });


    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_default_not_found)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mAppContext).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
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

}
