package com.wujiemall.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.common.CommonDialog;
import com.wujiemall.order.ui.rownumber.AtyEnterNumber;
import com.wujiemall.order.ui.rownumber.AtyMsgDetails;
import com.wujiemall.order.utils.ToastUitl;

import java.util.List;

/**
 * 创建者：Qyl
 * 创建时间：2018/7/2 0002 10:54
 * 功能描述：首页列表adapter
 * 联系方式：无
 */
public class MainRowNumberAdapter extends RecyclerView.Adapter implements View.OnClickListener, CommonDialog.DialogSure, CommonDialog.DialogCancle {
    private Context context;
    private List list;
    private int type;
    private CommonDialog commonDialog;

    public MainRowNumberAdapter(Context context, List list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_number_list_item, parent, false));
        if (holder==null){
            return null;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holders = (MyViewHolder) holder;
        if (type == 0) {
            holders.nderwayNumber.setVisibility(View.VISIBLE);
            holders.alreadyEnterNumber.setVisibility(View.GONE);
            holders.alreadyTooNumber.setVisibility(View.GONE);
        } else if (type == 1) {
            holders.nderwayNumber.setVisibility(View.GONE);
            holders.alreadyEnterNumber.setVisibility(View.VISIBLE);
            holders.alreadyTooNumber.setVisibility(View.GONE);
        } else {
            holders.nderwayNumber.setVisibility(View.GONE);
            holders.alreadyEnterNumber.setVisibility(View.GONE);
            holders.alreadyTooNumber.setVisibility(View.VISIBLE);
        }
        holders.goMsgDetails.setOnClickListener(this);
        holders.callNumber.setOnClickListener(this);
        holders.enterNumber.setOnClickListener(this);
        holders.tooNumber.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_call_number:
//                DialogUtil.showCallDialog(context);
                View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_show_message_content, null);
                TextView showMsgTv = contentView.findViewById(R.id.dialogShowMessage_show_tv);
                showMsgTv.setText("叫号无人应答，确认过号。");
                commonDialog = new CommonDialog(context, "确定过号", "取消", "提醒", contentView, 0.8f, this, this);
                commonDialog.show();
                break;
            case R.id.item_enter_number:
                context.startActivity(new Intent(context, AtyEnterNumber.class));
                break;
            case R.id.item_too_number:
                break;
            case R.id.go_msg_details:
                Intent intent = new Intent();
                intent.setClass(context, AtyMsgDetails.class);
                context.startActivity(intent);
                break;
        }
    }

    @Override
    public void dialogSure() {
        commonDialog.dissMiss();
        ToastUitl.show("已经过号", 0);
    }

    @Override
    public void dialogCancle() {
        commonDialog.dissMiss();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout nderwayNumber, alreadyTooNumber, alreadyEnterNumber, goMsgDetails;
        private TextView callNumber, enterNumber, tooNumber;


        public MyViewHolder(View itemView) {
            super(itemView);
            nderwayNumber = itemView.findViewById(R.id.item_nderway_rownumber);
            alreadyTooNumber = itemView.findViewById(R.id.item_already_too_number);
            alreadyEnterNumber = itemView.findViewById(R.id.item_already_enter_number);
            goMsgDetails = itemView.findViewById(R.id.go_msg_details);
            //叫号
            callNumber = itemView.findViewById(R.id.item_call_number);
            //入号
            enterNumber = itemView.findViewById(R.id.item_enter_number);
            //过号
            tooNumber = itemView.findViewById(R.id.item_too_number);
        }
    }
}
