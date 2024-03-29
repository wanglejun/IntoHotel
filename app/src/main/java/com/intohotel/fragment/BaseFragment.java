package com.intohotel.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intohotel.R;
import com.intohotel.activity.MainActivity;
import com.intohotel.utils.ActivityIntentUtils;

/*
* Fragment基类
* */
public abstract class BaseFragment extends Fragment {

    public ActivityIntentUtils activityIntent;
    public MainActivity mainActivity;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
        activityIntent = new ActivityIntentUtils(mainActivity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    protected void init(){
        loadViewLayout();
        setListener();
        initData();
    }
    /**
     * 功能描述：<加载页面View>
     */
    protected abstract void loadViewLayout();

    /**
     * 功能描述：<初始化数据>
     */
    protected abstract void initData();

    /**
     * 功能描述：<设置监听>
     */
    protected abstract void setListener();

}
