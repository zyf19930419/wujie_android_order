package com.wujiemall.order.ui.banquet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;
import com.wujiemall.order.base.BaseRecycleAdapter;

import java.util.ArrayList;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/2 14:12
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class BanquetTableActivity extends BaseActivity implements View.OnClickListener, BaseRecycleAdapter.ItemClickListener {
    private RecyclerView banquet_table_Rv;
    private BanqueTableAdapter banqueTableAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_banquet_table;
    }

    @Override
    public void initView() {
        titleSetting("宴会台", "添加宴会", this, R.color.title_redF23030);
        banquet_table_Rv = findViewById(R.id.banquet_table_Rv);
        banqueTableAdapter = new BanqueTableAdapter(BanquetTableActivity.this, BanquetTableActivity.this, null);
        banquet_table_Rv.setLayoutManager(new LinearLayoutManager(BanquetTableActivity.this, LinearLayoutManager.VERTICAL, false));
        banquet_table_Rv.setAdapter(banqueTableAdapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ArrayList<BanqueTableBean> banqueTableBeans=new ArrayList<>();
        for(int i=0;i<10;i++){
            BanqueTableBean banqueTableBean=new BanqueTableBean();
            banqueTableBean.setData("2017/04/08  午餐");
            banqueTableBean.setName("王凯旋");
            banqueTableBean.setPhoneNo("15081138523");
            ArrayList<String>  nos=new ArrayList<>();
            for(int j=0;j<3;j++){
                nos.add("A-00"+(j+1));
            }
            banqueTableBean.setReTableNo(nos);
            banqueTableBeans.add(banqueTableBean);
        }
        banqueTableAdapter.setList(banqueTableBeans);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(int position, BaseRecycleAdapter adapter) {
        if (adapter instanceof BanqueTableAdapter) {

        }
    }

    private class BanqueTableAdapter extends BaseRecycleAdapter<BanqueTableBean> {

        /**
         * @param context
         * @param itemClickListener
         * @param itemLongClickListener
         */
        protected BanqueTableAdapter(Context context, ItemClickListener itemClickListener, ItemLongClickListener itemLongClickListener) {
            super(context, itemClickListener, itemLongClickListener);
        }

        @Override
        public RecyclerView.ViewHolder initHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_banquet_table, parent, false);
            BanqueTableHolder banqueTableHolder = new BanqueTableHolder(view);
            return banqueTableHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            BanqueTableHolder banqueTableHolder = (BanqueTableHolder) holder;
            BanqueTableBean banqueTableBean = mList.get(position);
            banqueTableHolder.titleTv.setText(banqueTableBean.getData());
            banqueTableHolder.name_phoneTv.setText(banqueTableBean.getName() + "\t" + banqueTableBean.getPhoneNo());

            StringBuffer noBuffer = new StringBuffer();
            int i = 0;
            for (String no :
                    banqueTableBean.getReTableNo()) {
                noBuffer.append(no);
                if (i != banqueTableBean.getPhoneNo().length() - 1) {
                    noBuffer.append("、");
                }
            }
            banqueTableHolder.reTableNoTv.setText(Html.fromHtml("<font color=\"#999999\">预定桌位:</font>\t" + "<font color=\"#333333\">" + noBuffer + "</font>"));

            banqueTableHolder.see_detailTv.setTag(position);
            if (null != itemClickListener) {
                banqueTableHolder.see_detailTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(Integer.parseInt(String.valueOf(v.getTag())), BanqueTableAdapter.this);
                    }
                });
            }
        }

        private class BanqueTableHolder extends RecyclerView.ViewHolder {
            private TextView titleTv, name_phoneTv, reTableNoTv, see_detailTv;

            public BanqueTableHolder(View itemView) {
                super(itemView);
                titleTv = itemView.findViewById(R.id.titleTv);
                name_phoneTv = itemView.findViewById(R.id.name_phoneTv);
                reTableNoTv = itemView.findViewById(R.id.reTableNoTv);
                see_detailTv = itemView.findViewById(R.id.see_detailTv);

            }
        }
    }
}
