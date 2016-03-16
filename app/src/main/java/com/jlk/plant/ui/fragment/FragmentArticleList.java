package com.jlk.plant.ui.fragment;

import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragment;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentArticleList extends BaseFragment {
    private String tag = "FragmentThree";
    private TextView title;// 标题

    private int typeId = 0;

    @Override
    public void initData() {
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {
        typeId = getArguments().getInt("typeId");
        TextView text= (TextView) mRootView.findViewById(R.id.textView);
        text.setText("typeId="+ typeId);

    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_list;
    }

}
