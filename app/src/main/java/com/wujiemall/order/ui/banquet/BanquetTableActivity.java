package com.wujiemall.order.ui.banquet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wujiemall.order.R;
import com.wujiemall.order.base.BaseActivity;

/**
 * 创建者：TJDragon(LiuGang)
 * 创建时间：2018/7/2 14:12
 * 功能描述：
 * 联系方式：常用邮箱或电话
 */
public class BanquetTableActivity extends BaseActivity implements View.OnClickListener{
    private RecyclerView banquet_table_Rv;
    @Override
    public int getLayoutId() {
        return R.layout.activity_banquet_table;
    }

    @Override
    public void initView() {
        titleSetting("宴会台","添加宴会",this,R.color.title_redF23030);
        banquet_table_Rv=findViewById(R.id.banquet_table_Rv);
//        banquet_table_Rv.setAdapter();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {

    }
    private class BanqueTableAdapter extends RecyclerView.Adapter{
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
        private class BanqueTableHolder extends RecyclerView.ViewHolder{

            public BanqueTableHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
