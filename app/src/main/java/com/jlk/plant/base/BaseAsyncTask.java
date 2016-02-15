package com.jlk.plant.base;

import android.os.AsyncTask;

/**
 * 异步任务（带回调接口）
 *
 */
public class BaseAsyncTask extends AsyncTask <Object, Object, Object> {
	private OnPostExecuteListener postExecuteListener;
	private OnPostFailListener postFailListener;
		
	@Override
	protected Object doInBackground(Object... params) {
		return null;
	}
	
	/**
	 * 调用成功回调
	 * @author JHong
	 *
	 */
	protected void onPostExecute(Object paramObject) {
		super.onPostExecute(paramObject);
		if (this.postExecuteListener != null)
			this.postExecuteListener.onPostExecuteListener(paramObject);
	}
	
	/**
	 * 调用失败回调
	 * @author JHong
	 *
	 */
	protected void onPostFail(Object paramObject) {
		super.onPostExecute(paramObject);
		if (this.postFailListener != null)
			this.postFailListener.onPostFailListener(paramObject);;
	}

	/**
	 * 设置调用成功监听器
	 * @author JHong
	 *
	 */
	public void setOnPostExecuteListener(OnPostExecuteListener paramOnPostExecuteListener) {
		if (paramOnPostExecuteListener != null) {
			this.postExecuteListener = paramOnPostExecuteListener;
			return;
		}
		throw new NullPointerException("OnPostExecuteListener is null");
	}
	
	/**
	 * 设置调用失败监听器
	 * @author JHong
	 *
	 */
	public void setOnPostFailListener(OnPostFailListener paramOnPostFailListener) {
		if (paramOnPostFailListener != null) {
			this.postFailListener = paramOnPostFailListener;
			return;
		}
		throw new NullPointerException("OnPostFailListener is null");
	}

	/**
	 * 调用成功回调接口
	 * @author JHong
	 *
	 */
	public static abstract interface OnPostExecuteListener {
		public abstract void onPostExecuteListener(Object paramObject);
	}

	/**
	 * 调用失败回调接口
	 * @author JHong
	 *
	 */
	public static abstract interface OnPostFailListener {
		public abstract void onPostFailListener(Object paramObject);
	}
	
	
}