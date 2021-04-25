package org.zouyi.stockinvestment.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.zouyi.stockinvestment.BaseFragment;
import org.zouyi.stockinvestment.R;
import org.zouyi.stockinvestment.view.PullFlashLoadMoreListView;


/**
 * DCF首页
 */
public class HomeFragment extends BaseFragment {
    
    private View view;

    private EditText et_csxjl;
    private EditText et_n;
    private EditText et_r;
    private EditText et_i;
    private EditText et_d;
    private EditText et_totalstock;

    private TextView tv_result;
    private TextView tv_priceperstock;
    private TextView tv_pe;
    private Button btn_cal;

    public static HomeFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString("key", data);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view =inflater.inflate(R.layout.fragment_home, container, false);
        tvTitle.setText(getArguments().getString("key"));
        flContent.addView(view);
        initView();
        return baseView;
    }

    private void initView(){
        et_csxjl = view.findViewById(R.id.et_csxjl);
        et_n= view.findViewById(R.id.et_n);
        et_r= view.findViewById(R.id.et_r);
        et_i= view.findViewById(R.id.et_i);
        et_d= view.findViewById(R.id.et_d);
        et_totalstock= view.findViewById(R.id.et_totalstock);

        tv_result= view.findViewById(R.id.tv_result);
        tv_priceperstock= view.findViewById(R.id.tv_priceperstock);
        tv_pe= view.findViewById(R.id.tv_pe);

        btn_cal= view.findViewById(R.id.btn_cal);
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calDCF();
            }
        });
    }

    private void calDCF(){
        String str_csxjl = et_csxjl.getText().toString().trim();
        if(str_csxjl==null || "".equals(str_csxjl)){
            centerToast("请输入初始现金流");
            return;
        }
        double csxjl = Double.valueOf(str_csxjl).doubleValue();

        String str_n = et_n.getText().toString().trim();
        if(str_n==null || "".equals(str_n)){
            centerToast("请输入年限N");
            return;
        }
        int n =     Integer.valueOf(str_n).intValue();

        String str_r = et_r.getText().toString().trim();
        if(str_r==null || "".equals(str_r)){
            centerToast("请输入N年内增长率");
            return;
        }
        double nr =   Double.valueOf(str_r).doubleValue()/100;

        String str_i = et_i.getText().toString().trim();
        if(str_i==null || "".equals(str_i)){
            centerToast("请输入永续增长率");
            return;
        }
        double yr =   Double.valueOf(str_i).doubleValue()/100;

        String str_d = et_d.getText().toString().trim();
        if(str_d==null || "".equals(str_d)){
            centerToast("请输入折现率");
            return;
        }
        double d =   Double.valueOf(str_d).doubleValue()/100;

        String str_totalstock = et_totalstock.getText().toString().trim();
        if(str_totalstock==null || "".equals(str_totalstock)){
            centerToast("请输入总股本");
            return;
        }
        double totalstock =   Double.valueOf(str_totalstock).doubleValue();

        //企业最终价值
        double qiye_value=0;
        //第n年的自由现金流
        double jxl_n=0;

        //计算年内自由现金流折现值
        for(int i=0;i<n;i++){
            double jxl_zxz = csxjl * Math.pow(1+nr, i+1)/ Math.pow(1+d, i+1);
            qiye_value +=jxl_zxz;
            if(i==n-1){
                jxl_n = csxjl * Math.pow(1+nr, i+1);
            }
        }
        //计算永续自由现金流折现值
        double yx_zxz =jxl_n * (1+yr)/(d - yr)/ Math.pow(1+d, n+1);
        qiye_value +=yx_zxz;

        //四舍五入保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");

        //计算每个价格
        double pricePerStock = qiye_value / totalstock;
        //计算市盈率:暂时用企业估值除以初始现金流
        double PE = qiye_value/csxjl;

        tv_result.setText("企业价值为："+df.format(qiye_value)+"亿");
        tv_priceperstock.setText("每股价格："+df.format(pricePerStock)+"元");
        tv_pe.setText("市盈率："+df.format(PE));
    }

    private void centerToast(String message){
        Toast toast =Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
