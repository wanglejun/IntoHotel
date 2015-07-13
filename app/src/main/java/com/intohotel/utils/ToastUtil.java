package com.intohotel.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 
 * <Tosat消息工具>
 */
public class ToastUtil {
	private static final int TIME = 1000;

	public static void show(Context context, int resId) {
		Toast.makeText(context, context.getResources().getText(resId), TIME)
				.show();
	}

	public static void show(Context context, int resId, int showTime) {
		Toast.makeText(context, context.getResources().getText(resId), showTime)
				.show();
	}

	public static void show(Context context, String str) {
		Toast.makeText(context, str, TIME).show();
	}

	public static void show(Context context, String str, int showTime) {
		Toast.makeText(context, str, showTime).show();
	}

	/**
	 * 显示提示信息，需要在线程中显示Toast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(final Context context, final String msg) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}).start();
	}

	/**
	 * 显示提示信息，需要在线程中显示Toast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(final Context context, final int resId) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(context, context.getResources().getText(resId),
						Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}).start();
	}
}
