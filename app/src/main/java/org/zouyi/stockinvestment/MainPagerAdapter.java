package org.zouyi.stockinvestment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-8-18.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private List<Fragment> mFragments;
    private int[] iconUnSelected;
    private int[] iconSelected;
    private String[] mTabNames;
    private final List<ImageView> mListImgIcon = new ArrayList<>();
    private final List<TextView> mListName = new ArrayList<>();

    /**
     * @param context 上下文
     * @param fm FragmentManager
     * @param fragments 内容区Fragment
     * @param mTabNames 底部标签名
     * @param iconUnSelected 标签未被选中图标
     * @param iconSelected 标签被选中图标
     */
    public MainPagerAdapter(Context context, FragmentManager fm,
                            List<Fragment> fragments, String[] mTabNames,
                            int[] iconUnSelected, int[] iconSelected) {
        super(fm);
        this.context=context;
        this.mFragments = fragments;
        this.mTabNames = mTabNames;
        this.iconUnSelected = iconUnSelected;
        this.iconSelected = iconSelected;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabNames.length==mFragments.size()?mFragments.size():0;
    }

    /**
     * 重写此方法，返回TabLayout的内容
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    /**
     * 自定义tab样式
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View view = View.inflate(context, R.layout.activity_main_tab_item, null);

        ImageView img_icon = (ImageView) view.findViewById(R.id.main_tab_img_icon);
        TextView tv_name = (TextView) view.findViewById(R.id.main_tab_tv_name);
        tv_name.setText(mTabNames[position]);
        img_icon.setImageResource(iconUnSelected[position]);
        mListName.add(tv_name);
        mListImgIcon.add(img_icon);
        return view;
    }

    /**
     * 当标签被选中时
     * @param postion
     */
    public void setTabSelected(int postion) {
        mListName.get(postion).setTextColor(Color.parseColor("#1296db"));
        mListImgIcon.get(postion).setImageResource(iconSelected[postion]);
    }

    /**
     * 当标签未被选中时
     * @param postion
     */
    public void setTabUnSelected(int postion) {
        mListName.get(postion).setTextColor(Color.parseColor("#666666"));
        mListImgIcon.get(postion).setImageResource(iconUnSelected[postion]);
    }
}
