package com.jlk.plant.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jlk.plant.R;

public abstract class RefreshFragmentActivity extends BaseFragmentActivity {
	private final String TAG = "RefreshFragmentActivity";
	protected View mRootView;
	protected LinearLayout mContentContainer;
	private RelativeLayout mRefresh;

	protected boolean isLoadSuccess;

	protected int getFrameLayoutId() {
		return R.layout.fragmentactivity_refresh;
	}

	@Override
	public void setContentView() {
		super.setContentView(getFrameLayoutId());

		mContentContainer = (LinearLayout) findViewById(R.id.layout_content);
		mContentContainer.setVisibility(View.VISIBLE);
		mRefresh = (RelativeLayout) findViewById(R.id.layout_load_fail);
		mRefresh.setVisibility(View.GONE);
		mRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				reloadDataFromNetWord();
			}
		});

		setContentView(setChildLayoutId());
	}

	/**
	 * 子类布局资源ID
	 * 
	 * @return
	 */
	public abstract int setChildLayoutId();

	/**
	 * 判断网络数据是否加载成功
	 * 
	 * @return
	 */
	public abstract boolean isLoadAllSuccess();

	/**
	 * 重新加载网络数据
	 * 
	 * @return
	 */
	public abstract void reloadDataFromNetWord();

	@Override
	public void setContentView(int layoutResID) {
		View view = LayoutInflater.from(this).inflate(layoutResID, null);
		mContentContainer.addView(view);
	}

	public void showReloadData() {
		if (isLoadAllSuccess()) {
			mRefresh.setVisibility(View.GONE);
			mContentContainer.setVisibility(View.VISIBLE);
		}
	}

	public void showLoadFailText() {
		mRefresh.setVisibility(View.VISIBLE);
		mContentContainer.setVisibility(View.GONE);
	}
}
