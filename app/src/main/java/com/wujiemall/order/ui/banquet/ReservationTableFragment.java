package com.wujiemall.order.ui.banquet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.adapter.OnItemClickListener;
import com.wujiemall.order.base.BaseFragment;
import com.wujiemall.order.base.BaseRecycleAdapter;
import com.wujiemall.order.common.MallRecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/5 11:59
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class ReservationTableFragment extends BaseFragment implements View.OnClickListener, ResListener {
    private RecyclerView resTableRv;
    private ImageView choiceImg;
    private TextView allChoiceTv, sureTv, statisticschoiceTv;
    private View allchoiceLayout;
    private ResTableAdapter resTableAdapter;

    public interface Listener {

        /**
         * 确定按钮点击事件
         *
         * @param tableNoStr 返回桌号用“,”分割
         * @param type       类型
         */
        public void sureListener(String tableNoStr, int type);
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static ReservationTableFragment newInstance(int type) {
        ReservationTableFragment reservationTableFragment = new ReservationTableFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        reservationTableFragment.setArguments(bundle);
        return reservationTableFragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragemnt_reservation_table;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View v) {
        resTableRv = v.findViewById(R.id.resTableRv);
        resTableRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int dip = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1,
                getContext().getResources().getDisplayMetrics());
        resTableRv.addItemDecoration(new MallRecyclerViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, dip, R.color.D2D2D2, -1));
        resTableAdapter = new ResTableAdapter(getContext(), null, null, this);
        resTableRv.setAdapter(resTableAdapter);
        sureTv = v.findViewById(R.id.sureTv);
        allChoiceTv = v.findViewById(R.id.allChoiceTv);
        choiceImg = v.findViewById(R.id.choiceImg);
        statisticschoiceTv = v.findViewById(R.id.statisticschoiceTv);
        allchoiceLayout = v.findViewById(R.id.allchoiceLayout);
        allchoiceLayout.setOnClickListener(this);
        sureTv.setOnClickListener(this);

        List<TableBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TableBean tableBean = new TableBean();
            tableBean.setIsChoice(0);
            tableBean.setState(i % 2);
            tableBean.setTableNo(String.valueOf(getArguments().get("type")) + "-00" + i);
            list.add(tableBean);
        }
        resTableAdapter.setList(list);
    }

    @Override
    public void setOutChoice(boolean choice) {
        choiceImg.setSelected(choice);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(null!=resTableAdapter){
            resTableAdapter.clacTableNum();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allchoiceLayout: {//全选
                choiceImg.setSelected(!choiceImg.isSelected());
                resTableAdapter.allChoice(choiceImg.isSelected() ? 1 : 0);
            }
            break;
            case R.id.sureTv: {
                listener.sureListener(resTableAdapter.statisticsTableNo(), (int) getArguments().get("type"));
            }
            break;
        }
    }

    /**
     * 选择桌号适配器
     */
    private class ResTableAdapter extends BaseRecycleAdapter<TableBean> {
        private int utilize, free;
        private ResListener listener;

        /**
         * @param context
         * @param itemClickListener
         * @param itemLongClickListener
         */
        protected ResTableAdapter(Context context, ItemClickListener itemClickListener, ItemLongClickListener itemLongClickListener, ResListener listener) {
            super(context, itemClickListener, itemLongClickListener);
            utilize = context.getResources().getColor(R.color.colorE00427);
            free = context.getResources().getColor(R.color.color07BD18);
            this.listener = listener;
        }

        /**
         * 统计桌号
         *
         * @return
         */
        public String statisticsTableNo() {
            StringBuffer stringBuffer = new StringBuffer();
            for (TableBean tableBean :
                    mList) {
                if (tableBean.getIsChoice() == 1) {
                    stringBuffer.append(tableBean.getTableNo());
                    stringBuffer.append(",");
                }
            }
            return String.valueOf(stringBuffer.subSequence(0, stringBuffer.length() - 1));//去掉最后一个“,”
        }

        /**
         * 选择全部
         *
         * @param choice
         */
        public void allChoice(int choice) {
            List<TableBean> list = getList();
            if (null != list) {
                for (TableBean tableBean : list
                        ) {
                    tableBean.setIsChoice(choice);
                }
                notifyDataSetChanged();
            }
            clacTableNum();
        }

        /**
         * 自身选择单项时候进行判断，控制外部全选按钮联动
         */
        private void judgeOutChoice() {
            boolean outChoice = true;//外部全选按钮是否选中

            for (int i = 0; i < mList.size(); i++) {
                TableBean tableBean = mList.get(i);
                if (0 == tableBean.getIsChoice()) {
                    //非全选
                    outChoice = false;
                }
                break;
            }
            listener.setOutChoice(outChoice);
            clacTableNum();
        }

        /**
         * 计算总共点了几桌
         */
        public void clacTableNum() {
            ArrayList<TableBean> recordChoiceTables = new ArrayList<>();
            for (int i = 0; i < mList.size(); i++) {
                TableBean tableBean = mList.get(i);
                if (1 == tableBean.getIsChoice()) {
                    recordChoiceTables.add(tableBean);
                }
            }
            ResTableController.gainInstance().setTables(recordChoiceTables, (int) getArguments().get("type"));
            statisticschoiceTv.setText("共选择 " + ResTableController.gainInstance().getTotal() + " 个桌位");
        }

        @Override
        public RecyclerView.ViewHolder initHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_res_table, parent, false);
            return new ResHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            ResHolder resHolder = (ResHolder) holder;
            TableBean tableBean = (TableBean) mList.get(position);
            //单项是否选中
            resHolder.choiceImg.setSelected(tableBean.getIsChoice() == 1 ? true : false);
            //桌位空闲还是利用
            if (tableBean.getState() == 0) {
                resHolder.stateTv.setText("空闲");
                resHolder.stateTv.setTextColor(free);
            } else {
                resHolder.stateTv.setText("利用");
                resHolder.stateTv.setTextColor(utilize);
            }
            //桌号名称
            resHolder.tableNoTv.setText(tableBean.getTableNo());
            //单条点击事件
            resHolder.itemView.setTag(position);
            resHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    TableBean tableBean1 = mList.get(pos);
//                    反选单条目
                    tableBean1.setIsChoice(tableBean1.getIsChoice() == 1 ? 0 : 1);
                    notifyDataSetChanged();
//                    改变外层全选按钮点击状态
                    judgeOutChoice();
                }
            });

        }

        class ResHolder extends RecyclerView.ViewHolder {
            private ImageView choiceImg;
            private TextView tableNoTv;
            private TextView stateTv;

            public ResHolder(View itemView) {
                super(itemView);
                choiceImg = itemView.findViewById(R.id.choiceImg);
                tableNoTv = itemView.findViewById(R.id.tableNoTv);
                stateTv = itemView.findViewById(R.id.stateTv);
            }
        }
    }


}
