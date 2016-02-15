package com.jlk.plant.ui.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


/**
 * 健康管理fragment
 * 
 * @author jlk
 * 
 */
public class FragmentTwo extends BaseFragment {
	private String TAG = "FragmentTwo";
	private TextView title;// 标题
private ImageView imageView;
	@Override
	public void initData() {

	}

	@Override
	public void initListeners() {

	}

	@Override
	public void initViews() {
		imageView=(ImageView)mRootView.findViewById(R.id.imageView);

		//网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
		DisplayImageOptions options = new DisplayImageOptions.Builder().
				showImageForEmptyUri(R.mipmap.ic_default_adimage).displayer(new RoundedBitmapDisplayer(3))//是否设置为圆角，弧度为多少
				.cacheInMemory(true).cacheOnDisk(true).build();

		ImageLoader.getInstance().displayImage("http://map1.zw3e.com:8080/zw_news_map/200/2014073/1406294264986252304.jpg",imageView,options);

	}

	@Override
	public int setRootViewResourceId() {
		return R.layout.fragment_two;
	}

}
