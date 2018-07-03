package com.wujiemall.order.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wujiemall.order.R;

import java.util.List;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/3 0003 14:52
 * 功能描述：
 * 联系方式：无
 */
public class OrderMainAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List list;
    private int type;
    private OListener oListener;

    public OrderMainAdapter(Context context, List list, int type, OListener listener) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.oListener = listener;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public int getType() {
        return type;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolde holde = null;

        int type = getType();
        switch (type) {
            case 0:
                if (convertView == null) {
                    holde = new MyHolde();
                    convertView = convertView.inflate(context, R.layout.order_main_gridlist_item, null);
                    holde.tableDetails = convertView.findViewById(R.id.order_table_details);
                    holde.tableType = convertView.findViewById(R.id.order_table_type);
                    holde.tableStart = convertView.findViewById(R.id.order_table_start);
                    holde.tableDetails.setVisibility(View.GONE);
                    holde.tableStart.setVisibility(View.VISIBLE);
                    convertView.setTag(holde);
                } else {
                    holde = (MyHolde) convertView.getTag();
                }
                break;
            case 1:
                if (convertView == null) {
                    holde = new MyHolde();
                    convertView = convertView.inflate(context, R.layout.order_main_gridlist_item, null);
                    holde.tableDetails = convertView.findViewById(R.id.order_table_details);
                    holde.tableType = convertView.findViewById(R.id.order_table_type);
                    holde.tableStart = convertView.findViewById(R.id.order_table_start);
                    holde.tableDetails.setVisibility(View.VISIBLE);
                    holde.tableStart.setVisibility(View.GONE);
                    convertView.setTag(holde);
                } else {
                    holde = (MyHolde) convertView.getTag();
                }
                break;
        }
        if (convertView == null) {
            holde = new MyHolde();
            convertView = convertView.inflate(context, R.layout.order_main_gridlist_item, null);
            holde.tableDetails = convertView.findViewById(R.id.order_table_details);
            holde.tableType = convertView.findViewById(R.id.order_table_type);
            holde.tableStart = convertView.findViewById(R.id.order_table_start);
            holde.tableDetails.setVisibility(View.GONE);
            holde.tableStart.setVisibility(View.VISIBLE);
            convertView.setTag(holde);
        } else {
            holde = (MyHolde) convertView.getTag();
        }
        holde.tableStart.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        oListener.onClicks(v);
    }

    public interface OListener {
        void onClicks(View view);
    }

    public static class MyHolde {
        private LinearLayout tableDetails;
        private TextView tableType;
        private RelativeLayout tableStart;
    }
}
