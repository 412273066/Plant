package com.jlk.plant.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentTwo extends BaseFragment {
    private String tag = "FragmentTwo";
    private ListView listView;
    List list;
    private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };

    @Override
    public void initData() {
//        list = new ArrayList<Article>();
//        Article item;
//        for (int i = 0; i < 10; i++) {
//
//            item = new Article(i + "", "雷军内部讲话：去他妈的kpi，我们造机器人去了！", "2016-1-15"
//                    , "", "", "", "", "", "[2016小米闹天宫年会]雷军内部讲话实录。重新出发，大胆探索", "");
//            list.add(item);
//        }
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {
//        listView = (ListView) mRootView.findViewById(R.id.listView);
//        ListArticleAdapter adapter = new ListArticleAdapter(list, mContext);
//        listView.setAdapter(adapter);
        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getActivity().getSupportFragmentManager());

        ViewPager pager = (ViewPager)mRootView.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)mRootView.findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_two;
    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
}
