package org.zouyi.stockinvestment.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import org.zouyi.stockinvestment.R;

public class HomeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<String> messages;

    public HomeAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        messages = new ArrayList<String>();
        this.context = context;
    }

    public void loadData(List<String> messages) {
        if (messages != null) {
            this.messages = messages;
//            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_home_item, null);
            holder.ivHeadPhoto = (ImageView) convertView.findViewById(R.id.ivHeadPhoto);
            holder.tvNickname = (TextView) convertView.findViewById(R.id.tvNickname);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            holder.tvLastMessage = (TextView) convertView.findViewById(R.id.tvLastMessage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (messages.get(position)!= null) {
            holder.tvLastMessage.setText(position+messages.get(position));
        } else {
            holder.tvLastMessage.setText("");
        }
        return convertView;
    }

    static class ViewHolder {
        public ImageView ivHeadPhoto; //头像
        public TextView tvNickname; // 昵称
        public TextView tvTime;// 时间
        public TextView tvLastMessage;// 最新消息

    }
}
