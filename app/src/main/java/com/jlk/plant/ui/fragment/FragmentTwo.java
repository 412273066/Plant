package com.jlk.plant.ui.fragment;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jlk.plant.R;
import com.jlk.plant.adapter.ArticleTypeAdapter;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.models.ArticleType;
import com.jlk.plant.models.returnmodels.GetArticleTypeListReturn;
import com.jlk.plant.utils.L;
import com.jlk.plant.utils.OkHttpUtils;
import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentTwo extends BaseFragment {
    private String tag = "FragmentTwo";
    List<ArticleType> list;
    private ViewPager mViewPager;
    private TabPageIndicator indicator;
    private Handler mHandler;
    private ArticleTypeAdapter adapter;

    @Override
    public void initData() {
        mHandler = new Handler();

        list = new ArrayList<ArticleType>();
        //indicator先绑定viewpager，再更新
        adapter = new ArticleTypeAdapter(getActivity().getSupportFragmentManager(), list);
        mViewPager.setAdapter(adapter);
        indicator.setViewPager(mViewPager);
        initTypeData();
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {

        mViewPager = (ViewPager) mRootView.findViewById(R.id.pager);

        indicator = (TabPageIndicator) mRootView.findViewById(R.id.indicator);

    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_two;
    }

    private void initTypeData() {

        OkHttpUtils client = new OkHttpUtils(mContext, null, AppInterface.GETARTICLTTYPELIST);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(Call call, Response response) {
                try {
                    String json = response.body().string();
                    L.i("返回" + AppInterface.GETARTICLTTYPELIST + ":" + json);
                    Gson gson = new Gson();
                    final GetArticleTypeListReturn result = gson.fromJson(json, GetArticleTypeListReturn.class);

                    list.addAll((ArrayList<ArticleType>) result.getList());

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            if (list == null || list.size() == 0) {
                                Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();

                                return;
                            }
                            //两个都要notify，缺一不可，一个更新标签tab，一个更新fragment
                            adapter.notifyDataSetChanged();
                            indicator.notifyDataSetChanged();

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "接口出错，开发人员正在修复中。", Toast.LENGTH_SHORT).show();
                        }
                    });
                } finally {

                }

            }

            @Override
            public void onPostFailListener(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onPrePostListener() {

            }
        });


        client.doPost();

    }
}
