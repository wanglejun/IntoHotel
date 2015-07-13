package com.intohotel.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.intohotel.R;
/**
 * 
 * @ClassName CustomDialog
 * @Description 自定义dialog
 * @author 汪乐骏
 * @date 2014-2-27
 */
public class CustomDialog extends Dialog {

	public CustomDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	
	@SuppressLint("NewApi")
	public static class Builder {
		private Context context;
		//标题
		private String title;
		//显示message
		private String message;
		//确定
		private String positiveButtonText;
		//取消
		private String negativeButtonText;
		private View contentView;
		private OnClickListener positiveButtonClickListener;
		private OnClickListener negativeButtonClickListener;
		private OnDismissListener dismissListener;
		private LayoutInflater inflater;
		private CustomDialog dialog;
		public Builder(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
			 inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		/**
		 * Set the Dialog message from String
		 * 
		 * @param message
		 * @return
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * @param message
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Set a custom content view for the Dialog. If a message is set, the
		 * contentView is not added to the Dialog...
		 * @param v
		 * @return
		 */
		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText,
				OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the positive button text and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String positiveButtonText,
				OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(int negativeButtonText,
				OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button text and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String negativeButtonText,
				OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}
		
		public Builder setDismissListener(OnDismissListener dismissListener){
			this.dismissListener = dismissListener;
			return this;
		}
		
		public void dimiss(){
			if(dialog != null){
				dialog.dismiss();	
			}
		
		}
		
		/**
		 * 
		 * @Method: createLoadingDialog 
		 * @Description: loading dialog
		 * @param @return
		 * @return CustomDialog
		 * @throws
		 */
		public CustomDialog createLoadingDialog(String message){
			 LayoutInflater inflater = LayoutInflater.from(context);  
	        View view = inflater.inflate(R.layout.view_dialog_loading, null);// 得到加载view
	        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);// 加载布局  
	        ImageView spaceshipImage = (ImageView) view.findViewById(R.id.dialog_loading_img);  
	        TextView tipTextView = (TextView) view.findViewById(R.id.dialog_loading_text);// 提示文字  
	        // 加载动画  
	        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
	                context, R.anim.loading_animation);  
	        // 使用ImageView显示动画  
	        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
	        tipTextView.setText(message);// 设置加载信息  
		        
			dialog = new CustomDialog(context,R.style.loading_dialog);
			dialog.setCanceledOnTouchOutside(false);
			dialog.addContentView(layout, new LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.FILL_PARENT));
			dialog.setContentView(layout);
			return dialog;
		}
		
		/**
		 * 
		 * @Method: create 
		 * @Description: 创建dialog
		 * @param @return
		 * @return CustomDialog
		 * @throws
		 */
		public CustomDialog create() {
		
			// instantiate the dialog with the custom Theme
			dialog = new CustomDialog(context,R.style.Dialog);
			//禁止返回键取消
			dialog.setCancelable(false);
			View layout = inflater.inflate(R.layout.view_custom_dialog, null);
			dialog.addContentView(layout, new LayoutParams(
					android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		
			//确定按钮
			if (positiveButtonText != null) {
				((TextView) layout.findViewById(R.id.custom_dialog_confim_text))
						.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((LinearLayout) layout
							.findViewById(R.id.custom_dialog_confim_layout))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				//没有设置确定按钮text  隐藏
				layout.findViewById(R.id.custom_dialog_confim_layout)
						.setVisibility(View.GONE);
				layout.findViewById(R.id.custom_dialog_line_view)
				.setVisibility(View.GONE);
			}
			
			//取消按钮
			if (negativeButtonText != null) {
				((TextView) layout.findViewById(R.id.custom_dialog_cancle_text)).setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((LinearLayout) layout
							.findViewById(R.id.custom_dialog_cancle_layout))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				//没有设置取消按钮text  隐藏
				layout.findViewById(R.id.custom_dialog_cancle_layout)
						.setVisibility(View.GONE);
				layout.findViewById(R.id.custom_dialog_line_view)
				.setVisibility(View.GONE);
			}
	
			// 设置dialog content
			if (message != null) {
				((TextView) layout.findViewById(R.id.custom_dialog_content_text)).setText(message);
			} 

			//设置标题
			if(title != null){
				((TextView) layout.findViewById(R.id.custom_title_text)).setText(title);
			}
			
			if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body
				LinearLayout contentLayout = ((LinearLayout) layout.findViewById(R.id.custom_content_layout));
				contentLayout.removeAllViews();
				contentLayout.addView(contentView,new LayoutParams(
                        LayoutParams.FILL_PARENT, 
                        LayoutParams.WRAP_CONTENT));
			}
			dialog.setContentView(layout);
			return dialog;
		}
	}

	
	
}
