package org.zouyi.stockinvestment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zouyi.stockinvestment.BaseFragment;
import org.zouyi.stockinvestment.R;

/**'
 * 我
 */
public class MyFragment extends BaseFragment {

    private View view;

    public static MyFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString("key", data);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view =inflater.inflate(R.layout.fragment_my, container, false);
        tvTitle.setText(getArguments().getString("key"));
        flContent.addView(view);
        return baseView;
    }

}
