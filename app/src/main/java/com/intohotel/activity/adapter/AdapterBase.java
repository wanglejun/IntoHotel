package com.intohotel.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * 所有子类adapter继承该基类
 * @param <T>
 */
public abstract class AdapterBase<T> extends BaseAdapter {
	
	private final List<T> mList = new LinkedList<T>();
	public LayoutInflater inflater;
	public Context context;
	public AdapterBase(Context con){
		inflater = LayoutInflater.from(con);
		this.context = con;
	}
	
	public List<T> getList(){
		return mList;
	}
	
	public void appendToList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(list);
		notifyDataSetChanged();
	}
	public void clearTo(List<T> list) {
		if (list == null) {
			return;
		}
		mList.clear();
		notifyDataSetChanged();
		mList.addAll(list);
		notifyDataSetChanged();
	}

	public void appendToTopList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(0, list);
		notifyDataSetChanged();
	}

	public void clear() {
		mList.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(position > mList.size()-1){
			return null;
		}
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (position == getCount() - 1) {
//			onReachBottom();
		}
		return getExView(position, convertView, parent);
	}
	
	//针对不同的type进行显示。
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return position == 0 ? 0 : 1;
	}
	/** 
	 * @Method: getExView 
	 * @Description: 所有子类实现该基类。负责数据的绑定展现。
	 * @param @param position
	 * @param @param convertView
	 * @param @param parent
	 * @param @return
	 * @return View
	 * @throws 
	 */
	protected abstract View getExView(int position, View convertView, ViewGroup parent);
}
