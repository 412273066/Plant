package com.jlk.plant.ui.fragment;

import android.widget.ListView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.models.Article;

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
    List list;

    @Override
    public void initData() {
        list = new ArrayList<Article>();
        Article item;
        for (int i = 0; i < 10; i++) {

            item = new Article(i + "", "雷军内部讲话：去他妈的kpi，我们造机器人去了！", "2016-1-15"
                    , "", "", "", "", "", "[2016小米闹天宫年会]雷军内部讲话实录。重新出发，大胆探索", "");
            list.add(item);
        }
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {
        listView = (ListView) mRootView.findViewById(R.id.listView);
//        ListArticleAdapter adapter = new ListArticleAdapter(list, mContext);
//        listView.setAdapter(adapter);
    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_two;
    }

}
