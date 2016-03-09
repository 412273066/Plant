package com.jlk.plant.ui.fragment;

import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.adapter.MyPagingAdaper;
import com.jlk.plant.adapter.SafeAsyncTask;
import com.jlk.plant.base.BaseFragment;
import com.paging.listview.PagingListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentThree extends BaseFragment {
    private String tag = "FragmentThree";
    private TextView title;// 标题
    private PagingListView listView;
    private MyPagingAdaper adapter;

    private List<String> firstList;
    private List<String> secondList;
    private List<String> thirdList;

    private int pager = 0;
    @Override
    public void initData() {
        firstList = new ArrayList<String>();
        firstList.add("Afghanistan");
        firstList.add("Albania");
        firstList.add("Algeria");
        firstList.add("Andorra");
        firstList.add("Angola");
        firstList.add("Antigua and Barbuda");
        firstList.add("Argentina");
        firstList.add("Armenia");
        firstList.add("Aruba");
        firstList.add("Australia");
        firstList.add("Austria");
        firstList.add("Azerbaijan");

        secondList = new ArrayList<String>();
        secondList.add("Bahamas, The");
        secondList.add("Bahrain");
        secondList.add("Bangladesh");
        secondList.add("Barbados");
        secondList.add("Belarus");
        secondList.add("Belgium");
        secondList.add("Belize");
        secondList.add("Benin");
        secondList.add("Bhutan");
        secondList.add("Bolivia");
        secondList.add("Bosnia and Herzegovina");
        secondList.add("Botswana");
        secondList.add("Brazil");
        secondList.add("Brunei");
        secondList.add("Bulgaria");
        secondList.add("Burkina Faso");
        secondList.add("Burma");
        secondList.add("Burundi");

        thirdList = new ArrayList<String>();
        thirdList.add("Denmark");
        thirdList.add("Djibouti");
        thirdList.add("Dominica");
        thirdList.add("Dominican Republic");
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initViews() {
        listView = (PagingListView) mRootView.findViewById(R.id.paging_list_view);
        adapter = new MyPagingAdaper();

        listView.setAdapter(adapter);
        listView.setHasMoreItems(true);
        listView.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                if (pager < 3) {
                    new CountryAsyncTask().execute();
                } else {
                    listView.onFinishLoading(false, null);
                }
            }
        });
    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_three;
    }


    private class CountryAsyncTask extends SafeAsyncTask<List<String>> {

        @Override
        public List<String> call() throws Exception {
            List<String> result = null;
            switch (pager) {
                case 0:
                    result = firstList;
                    break;
                case 1:
                    result = secondList;
                    break;
                case 2:
                    result = thirdList;
                    break;
            }
            Thread.sleep(3000);
            return result;
        }

        @Override
        protected void onSuccess(List<String> newItems) throws Exception {
            super.onSuccess(newItems);
            pager++;
            listView.onFinishLoading(true, newItems);
        }
    }
}
