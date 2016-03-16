package com.jlk.plant.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jlk.plant.models.ArticleType;
import com.jlk.plant.ui.fragment.FragmentArticleList;

import java.util.List;

/**
 * Created by test on 2016/3/16.
 */
public class ArticleTypeAdapter extends FragmentPagerAdapter {
    private List<ArticleType> list;

    public ArticleTypeAdapter(FragmentManager fm) {
        super(fm);
    }

    public ArticleTypeAdapter(FragmentManager fm,  List<ArticleType> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentArticleList fragment = new FragmentArticleList();
        Bundle bundle = new Bundle();
        bundle.putInt("typeId", Integer.parseInt(list.get(position).getTypeId()));

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTypeName();
    }
}
