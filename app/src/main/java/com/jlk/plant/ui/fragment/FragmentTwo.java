package com.jlk.plant.ui.fragment;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import com.jlk.plant.R;
import com.jlk.plant.adapter.ArticleTypeAdapter;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.models.ArticleType;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentTwo extends BaseFragment {
    private String tag = "FragmentTwo";
    private ListView listView;
    List<ArticleType> list;
    private static final String[] CONTENT = new String[]{"养花基础", "养花知识", "植物趣闻", "植物科普"};
    private ViewPager pager;
    private TabPageIndicator indicator;

    @Override
    public void initData() {
        list = new ArrayList<ArticleType>();
        ArticleType item;
        for (int i = 0; i < 4; i++) {

            item = new ArticleType("" + i, CONTENT[i], "", "", "");
            list.add(item);
        }

        FragmentPagerAdapter adapter = new ArticleTypeAdapter(getActivity().getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {
//        listView = (ListView) mRootView.findViewById(R.id.listView);
//        ListArticleAdapter adapter = new ListArticleAdapter(list, mContext);
//        listView.setAdapter(adapter);


        pager = (ViewPager) mRootView.findViewById(R.id.pager);


        indicator = (TabPageIndicator) mRootView.findViewById(R.id.indicator);

    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_two;
    }


}
