package com.jlk.plant.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jlk.plant.R;


/**
 * Dialog工具类
 * 
 */
public class DialogUtil {

	public static DialogUtil instance;

	public static DialogUtil getInstance() {
		if (instance == null) {
			instance = new DialogUtil();
		}
		return instance;
	}

	/**
	 * Dialog基类
	 * 
	 * @param context
	 * @return
	 */
	public Dialog createBaseDialog(Context context) {
		Dialog dialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
		return dialog;
	}

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @return
	 */
	public Dialog createLoadingDialog(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.layout_custom_loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		TextView tipTextView = (TextView) v.findViewById(R.id.tip_text);// 提示文字
		tipTextView.setVisibility(View.GONE);

		Dialog loadingDialog = createBaseDialog(context);

		loadingDialog.setCancelable(true);// 可以用“返回键”取消
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;

	}

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public Dialog createLoadingDialog(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.layout_custom_loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		TextView tipTextView = (TextView) v.findViewById(R.id.tip_text);// 提示文字
		tipTextView.setText(msg);// 设置加载信息

		Dialog loadingDialog = createBaseDialog(context);

		loadingDialog.setCancelable(true);// 可以用“返回键”取消
		loadingDialog.setCanceledOnTouchOutside(false); //空白处 不可取消
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;

	}

	/**
	 * 带确定 取消按钮
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @return
	 */
	public Dialog createDialog(Context context, String title, String msg) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.layout_custom_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		TextView titleTextView = (TextView) v.findViewById(R.id.title_text);// 标题文字
		TextView tipTextView = (TextView) v.findViewById(R.id.tip_text);// 提示文字
		TextView commitBtn = (TextView) v.findViewById(R.id.commit_btn); // 确定按钮
		TextView cancelBtn = (TextView) v.findViewById(R.id.cancel_btn); // 取消按钮

		titleTextView.setText(title);// 标题文字信息
		tipTextView.setText(msg);// 提示文字信息

		final Dialog dialog = createBaseDialog(context);

		dialog.setCancelable(true);// 可以用“返回键”取消
		dialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (dialog.isShowing())
					dialog.dismiss();
			}
		});

		commitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (onCommitButtonListener != null) {
					dialog.dismiss();
					onCommitButtonListener.onCommitButtonListener(view);
				}

			}
		});

		return dialog;
	}

	/**
	 * 得到自定义的Dialog 只带确定按钮
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public Dialog createDialogCommitBtn(Context context, String title,
			String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.layout_custom_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		TextView titleTextView = (TextView) v.findViewById(R.id.title_text);// 标题文字
		TextView tipTextView = (TextView) v.findViewById(R.id.tip_text);// 提示文字
		TextView commitBtn = (TextView) v.findViewById(R.id.commit_btn); // 确定按钮
		TextView cancelBtn = (TextView) v.findViewById(R.id.cancel_btn); // 取消按钮
		cancelBtn.setVisibility(View.GONE);

		titleTextView.setText(title);// 标题文字信息
		tipTextView.setText(msg);// 提示文字信息

		final Dialog dialog = createBaseDialog(context);

		dialog.setCancelable(true);// 可以用“返回键”取消
		dialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

		commitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (dialog.isShowing())
					dialog.dismiss();
			}
		});

		return dialog;

	}

	/**
	 * 创建自定义的Dialog并定时消失 只带确定按钮
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public Dialog createDialogCommitBtnAutoDismiss(final Context context,
			String title, String msg) {
		final Handler handler = new Handler();

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.layout_custom_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		TextView titleTextView = (TextView) v.findViewById(R.id.title_text);// 标题文字
		TextView tipTextView = (TextView) v.findViewById(R.id.tip_text);// 提示文字
		final TextView commitBtn = (TextView) v.findViewById(R.id.commit_btn); // 确定按钮
		TextView cancelBtn = (TextView) v.findViewById(R.id.cancel_btn); // 取消按钮
		cancelBtn.setVisibility(View.GONE);

		titleTextView.setText(title);// 标题文字信息
		tipTextView.setText(msg);// 提示文字信息

		final Dialog dialog = createBaseDialog(context);
		dialog.setCancelable(true);// 可以用“返回键”取消
		dialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

		commitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
			}
		});
		dialog.show();

		final Runnable task = new Runnable() {
			private int i = 8;

			@Override
			public void run() {
				if (i > 0) {
					commitBtn.setText(context
							.getString(R.string.label_commit_button_text)
							+ " (" + i + ")");
					handler.postDelayed(this, 1000);
					i--;
				} else {
					if (dialog.isShowing())
						dialog.dismiss();
				}
			}
		};
		handler.post(task);

		return dialog;
	}

	public Dialog createListDialog(Context context, String title, View view,
			ListAdapter adapter) {
		TextView titleTextView = (TextView) view.findViewById(R.id.title_text);// 标题文字
		TextView cancelBtn = (TextView) view.findViewById(R.id.cancel_btn); // 取消按钮
		ListView listView = (ListView) view
				.findViewById(R.id.listview_dialog_content); // list

		if (adapter != null)
			listView.setAdapter(adapter);

		titleTextView.setText(title);// 标题文字信息

		final Dialog dialog = createBaseDialog(context);

		dialog.setCancelable(true);// 可以用“返回键”取消
		dialog.setContentView(view, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (dialog.isShowing())
					dialog.dismiss();
			}
		});

		return dialog;
	}

	/**
	 * 带确定 取消按钮  自定义 两个按钮文字
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @return
	 */
	public Dialog createDialog(Context context, String title, String msg,
			String positiveStr, String negativeStr) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.layout_custom_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		TextView titleTextView = (TextView) v.findViewById(R.id.title_text);// 标题文字
		TextView tipTextView = (TextView) v.findViewById(R.id.tip_text);// 提示文字
		TextView commitBtn = (TextView) v.findViewById(R.id.commit_btn); // 确定按钮
		TextView cancelBtn = (TextView) v.findViewById(R.id.cancel_btn); // 取消按钮

		titleTextView.setText(title);// 标题文字信息
		tipTextView.setText(msg);// 提示文字信息
		commitBtn.setText(positiveStr);
		cancelBtn.setText(negativeStr);

		final Dialog dialog = createBaseDialog(context);

		dialog.setCancelable(true);// 可以用“返回键”取消
		dialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (dialog.isShowing())
					dialog.dismiss();
			}
		});

		commitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (onCommitButtonListener != null) {
					dialog.dismiss();
					onCommitButtonListener.onCommitButtonListener(view);
				}

			}
		});

		return dialog;
	}

	public abstract interface OnCommitButtonListener { // 确定按钮回调接口
		public abstract void onCommitButtonListener(View view);
	}

	private OnCommitButtonListener onCommitButtonListener; // 确定按钮回调

	public void setOnCommitButtonListener(
			OnCommitButtonListener onCommitButtonListener) {
		if (onCommitButtonListener != null) {
			this.onCommitButtonListener = onCommitButtonListener;
			return;
		} else {
			throw new NullPointerException("OnCommitButtonListener is null");
		}

	}

}
