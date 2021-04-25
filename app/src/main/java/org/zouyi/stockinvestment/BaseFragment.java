package org.zouyi.stockinvestment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2018-8-18.
 */
public abstract class BaseFragment extends Fragment {

    protected  View baseView;
    protected FrameLayout flContent;
    protected TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_base, container, false);
        tvTitle = (TextView) baseView.findViewById(R.id.tvTitle);
        flContent = (FrameLayout) baseView.findViewById(R.id.flContent);
        return baseView;
    }
}
