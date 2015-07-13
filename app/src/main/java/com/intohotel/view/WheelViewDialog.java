package com.intohotel.view;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intohotel.R;
import com.intohotel.bean.WheelViewContentBean;

import java.util.ArrayList;

/**
 * 
 * @ClassName WheelViewDialog
 * @Description 滚轮对话框
 * @author 汪乐骏
 * @date 2015-3-26
 */
public class WheelViewDialog {
	private Context context;
	private int widthScreen;
	public IPositveBtnOnclick positveBtnOnclick;
	public WheelViewDialog(Context context,int widthScreen) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.widthScreen = widthScreen;
	}
	/**
	 *
	 * @Method: createWheelDialog 
	 * @Description: 创建不循环的单滚轮dialog, 用于比如性别（男，女）选择器
	 * @param @param cotentList
	 * @param @param title
	 * @param @param id
	 * @return void
	 * @throws
	 */
	public void createWheelDialogNoCyclic(final ArrayList<String> cotentList,String title,final int id) {
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				(int) ((((float) 500 / (float) 720) * (float) widthScreen)),
				LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 10, 0, 10);
		linearLayout.setLayoutParams(layoutParams);

		final WheelView wheelView = new WheelView(context);
		wheelView.setVisibleItems(5);
		wheelView.setCyclic(false);
		wheelView.setAdapter(new ArrayWheelAdapter<String>(cotentList));

		linearLayout.addView(wheelView, layoutParams);

		final CustomDialog.Builder builder = new CustomDialog.Builder(context);
		builder.setTitle(title);
		builder.setPositiveButton("完成", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				positveBtnOnclick.onPositveBtnOnclick(wheelView.getCurrentItem(),id);
				builder.dimiss();
			}
		});
		builder.setContentView(linearLayout);
		builder.create().show();
	}
	
	
	/**
	 * 
	 * @Method: createWheelsDialog 
	 * @Description: 创建多滚轮diaolog
	 * @param @param cotentList
	 * @param @param title
	 * @param @param id
	 * @return void
	 * @throws
	 */
	public void createWheelsDialog(final WheelViewContentBean wheelViewContentBean,String title,final int id) {
	
//		ArrayList<String> cotentList1 = new ArrayList<String>();
//		for (int i = 0; i < cotentList.size(); i++) {
//			cotentList1.add(cotentList.get(i).getContent());
//		}
		
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 10, 0, 10);
		layoutParams.weight = 1;
		linearLayout.setLayoutParams(layoutParams);

		final WheelView wheelView1 = new WheelView(context);
		wheelView1.setBackgroundColor(Color.BLACK);
		wheelView1.setVisibleItems(5);
		wheelView1.setCyclic(true);
		wheelView1.setAdapter(new ArrayWheelAdapter<String>(wheelViewContentBean.getConten1()));

		final WheelView wheelView2 = new WheelView(context);
		wheelView2.setBackgroundColor(Color.BLACK);
		wheelView2.setVisibleItems(5);
		wheelView2.setCyclic(true);
		wheelView2.setAdapter(new ArrayWheelAdapter<String>(wheelViewContentBean.getContent2()));

		final WheelView wheelView3 = new WheelView(context);
		wheelView3.setBackgroundColor(Color.BLACK);
		wheelView3.setVisibleItems(5);
		wheelView3.setCyclic(true);
		wheelView3.setAdapter(new ArrayWheelAdapter<String>(wheelViewContentBean.getContent3()));

		final WheelView wheelView4 = new WheelView(context);
		wheelView4.setBackgroundColor(Color.BLACK);
		wheelView4.setVisibleItems(5);
		wheelView4.setCyclic(true);
		wheelView4.setAdapter(new ArrayWheelAdapter<String>(wheelViewContentBean.getContent4()));

		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams2.gravity = Gravity.CENTER_VERTICAL;
		TextView textView = new TextView(context);
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setTextColor(context.getResources().getColor(R.color.black));
		textView.setText(".");
		textView.setTextSize(35);
		textView.setLayoutParams(layoutParams2);

		linearLayout.addView(wheelView1, layoutParams);
		linearLayout.addView(wheelView2, layoutParams);
		linearLayout.addView(wheelView3, layoutParams);
		linearLayout.addView(textView);
		linearLayout.addView(wheelView4, layoutParams);

		wheelView1.addChangingListener(new OnWheelChangedListener() {
			
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
//				wheelView2.setAdapter(new ArrayWheelAdapter<String>(cotentList.get(newValue).getChildContentList()));
//				wheelView2.setCurrentItem(cotentList.get(newValue).getChildContentList().size()/ 2);//选择中间的那个作为选中项
			}
		});

		final CustomDialog.Builder builder = new CustomDialog.Builder(context);
		builder.setTitle(title);
		builder.setPositiveButton("完成", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				positveBtnOnclick.onPositveBtnOnclick(wheelView1.getCurrentItem(),wheelView2.getCurrentItem(),wheelView3.getCurrentItem(),wheelView4.getCurrentItem(),id);
				builder.dimiss();
			}
		});
		builder.setContentView(linearLayout);
		builder.create().show();
	}
	
	/**
	 * 
	 * @ClassName IPositveBtnOnclick
	 * @Description 完成按钮接口
	 * @author 汪乐骏
	 * @date 2015-3-26
	 */
	public interface IPositveBtnOnclick{
		public void onPositveBtnOnclick(int position, int id);
		public void onPositveBtnOnclick(int position1, int positon2, int positon3, int positon4, int id);

	}

	public void setPositveBtnOnclick(IPositveBtnOnclick positveBtnOnclick) {
		this.positveBtnOnclick = positveBtnOnclick;
	}
	
}
