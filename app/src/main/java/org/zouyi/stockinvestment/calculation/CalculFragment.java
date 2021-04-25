package org.zouyi.stockinvestment.calculation;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.zouyi.stockinvestment.BaseFragment;
import org.zouyi.stockinvestment.R;

import java.text.DecimalFormat;


/**
 * 计算
 */
public class CalculFragment extends BaseFragment {
    
    private View view;

    private EditText et_year1;
    private EditText et_rate;
    private TextView tv_grow_multipl1;
    private Button btn_cal1;

    private EditText et_init_amount;
    private EditText et_end_amount;
    private EditText et_year2;
    private TextView tv_grow_multipl2;
    private TextView tv_compound_grow_rate;
    private Button btn_cal2;

    public static CalculFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString("key", data);
        CalculFragment fragment = new CalculFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view =inflater.inflate(R.layout.fragment_calcul, container, false);
        tvTitle.setText(getArguments().getString("key"));
        flContent.addView(view);
        initView();
        return baseView;
    }

    private void initView(){
        et_year1 = view.findViewById(R.id.et_year1);
        et_rate= view.findViewById(R.id.et_rate);
        tv_grow_multipl1= view.findViewById(R.id.tv_grow_multipl1);
        btn_cal1= view.findViewById(R.id.btn_cal1);
        btn_cal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculGrowMultipl();
            }
        });

        et_init_amount= view.findViewById(R.id.et_init_amount);
        et_end_amount= view.findViewById(R.id.et_end_amount);
        et_year2= view.findViewById(R.id.et_year2);
        tv_grow_multipl2= view.findViewById(R.id.tv_grow_multipl2);
        tv_compound_grow_rate= view.findViewById(R.id.tv_compound_grow_rate);
        btn_cal2= view.findViewById(R.id.btn_cal2);
        btn_cal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculCompoundGrowRate();
            }
        });
    }

    private void calculGrowMultipl(){
        String str_year1 = et_year1.getText().toString().trim();
        if(str_year1==null || "".equals(str_year1)){
            centerToast("预测年限");
            return;
        }
        double year1 = Integer.valueOf(str_year1).intValue();

        String str_rate = et_rate.getText().toString().trim();
        if(str_rate==null || "".equals(str_rate)){
            centerToast("请输入预测增长率");
            return;
        }
        double rate =   Double.valueOf(str_rate).doubleValue()/100;

        double growMultipl = Math.pow(1+rate,year1);

        DecimalFormat df = new DecimalFormat("#.00");
        tv_grow_multipl1.setText("增长倍数："+df.format(growMultipl));
    }

    private void calculCompoundGrowRate(){
        String str_init_amount = et_init_amount.getText().toString().trim();
        if(str_init_amount==null || "".equals(str_init_amount)){
            centerToast("请输入初始金额");
            return;
        }
        double init_amount = Double.valueOf(str_init_amount).doubleValue();

        String str_end_amount = et_end_amount.getText().toString().trim();
        if(str_end_amount==null || "".equals(str_end_amount)){
            centerToast("请输入期末金额");
            return;
        }
        double end_amount = Double.valueOf(str_end_amount).doubleValue();

        String str_year2 = et_year2.getText().toString().trim();
        if(str_year2==null || "".equals(str_year2)){
            centerToast("增长年限");
            return;
        }
        int year2 =   Integer.valueOf(str_year2).intValue();
        double grow_multipl2 = end_amount/init_amount;

        double compound_grow_rate = Math.pow(grow_multipl2,1.0/year2);//开根号
        compound_grow_rate = (compound_grow_rate -1)*100;

        //四舍五入保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        tv_grow_multipl2.setText("增长倍数："+df.format(grow_multipl2));
        tv_compound_grow_rate.setText("复合增长率："+df.format(compound_grow_rate)+"%");
    }

    private void centerToast(String message){
        Toast toast =Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
