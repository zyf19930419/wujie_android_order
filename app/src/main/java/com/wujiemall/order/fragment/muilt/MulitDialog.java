package com.wujiemall.order.fragment.muilt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wujiemall.order.R;
import com.wujiemall.order.common.DishAddReduceLayout;
import com.wujiemall.order.common.FullLinearLayoutManager;
import com.wujiemall.order.common.MallGridView;
import com.wujiemall.order.utils.DensityUtils;
import com.wujiemall.order.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OTKJ on 2018/4/10.
 */

public class MulitDialog extends Dialog implements View.OnClickListener {
    private List<MulitBean> mulitBeans;

    public MulitDialog(Activity context) {
        this(context, 0);
    }

    private RecyclerView dishRecycleView;
    private View view;

    public MulitDialog(Activity context, int themeResId) {
        super(context, R.style.DialogStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialogdishmulit, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.view = view;
        setContentView(view);
        mulitBeans = new ArrayList<>();
    }

    private DishBean dishBean;

    public void setDishBean(DishBean dishBean) {
        this.dishBean = dishBean;
        initData(view);
    }


    private int tpeZfPostion;

    private void initData(View view) {
        //设置标题
        final LinearLayout layoutTitle = view.findViewById(R.id.zfLayout);
        layoutTitle.getChildAt(tpeZfPostion).setSelected(true);
        for (int i = 0; i < layoutTitle.getChildCount(); i++) {
            TextView textView = (TextView) layoutTitle.getChildAt(i);
            if (i != tpeZfPostion) {
                textView.clearFocus();
                textView.setSelected(false);
            }
            textView.setTag(i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTitleClick(layoutTitle, (int) v.getTag());
                }
            });
        }
        //设置recyclerview
        dishRecycleView = view.findViewById(R.id.mulitRv);
        final MulitBean mulitBean = analogData(dishBean);
        DishMulitAdapter adpater = new DishMulitAdapter(mulitBean, view.getContext());
        adpater.analysisData(mulitBean);
        dishRecycleView.setAdapter(adpater);
        FullLinearLayoutManager manager = new FullLinearLayoutManager(getContext(), 0, 0);
        FullLinearLayoutManager.FullLinearLayoutListener fullLinearLayoutListener = new FullLinearLayoutManager.FullLinearLayoutListener() {
            @Override
            public void getWH(int[] getmeasuredDimension) {
                Window window = getWindow();//这部分是设置dialog宽高的，宽高是我从sharedpreferences里面获取到的。之前程序启动的时候有获取
                window.getDecorView().setPadding(0, 0, 0, 0);
                WindowManager.LayoutParams lp = window.getAttributes();
                DisplayMetrics size = DensityUtils.gainDM(getContext());
                lp.width = (int) (size.widthPixels * 0.8);
                if (getmeasuredDimension[1] > size.heightPixels * 0.4) {
                    //得到ListView的参数值
                    ViewGroup.LayoutParams params = dishRecycleView.getLayoutParams();
                    //设置ListView的高度是屏幕的一半
                    params.height = (int) (size.heightPixels * 0.4);
                    //设置
                    dishRecycleView.setLayoutParams(params);
                }
                window.setAttributes(lp);
                window.getAttributes().gravity = Gravity.CENTER;

            }
        };
        manager.setFullLinearLayoutListener(fullLinearLayoutListener);
        dishRecycleView.setLayoutManager(manager);

        //设置选择
        TextView choiceTv = view.findViewById(R.id.choiceTv);//已加菜品数量
        adpater.setAlreadyAddTv(choiceTv);
        DishAddReduceLayout addReduceLayout = view.findViewById(R.id.addReduceLayout);
        TextView num = view.findViewById(R.id.num);
        TextView totalPriceTv = view.findViewById(R.id.totalPriceTv);
        View choiceOverTv = view.findViewById(R.id.choiceOverTv);
        choiceOverTv.setOnClickListener(this);
        addReduceLayout.setListener(new DishAddReduceLayout.DishAddReduceListener() {
            @Override
            public void numChanged(int num, int position, boolean isAdd) {
                if (isAdd) {
                    DishMulitAdapter adapter = (DishMulitAdapter) dishRecycleView.getAdapter();
                    MulitBean mulitBean = adapter.getMulitBean();
                    mulitBeans.add(mulitBean);
                } else {
                    mulitBeans.remove(mulitBeans.get(mulitBeans.size() - 1));
                }
            }
        });
    }

    public void setMulitDialogListener(MulitDialogListener mulitDialogListener) {
        this.mulitDialogListener = mulitDialogListener;
    }

    private MulitDialogListener mulitDialogListener;

    public interface MulitDialogListener {
        public void addShoppingCat(MulitBean mulitBean);
    }

    class DishMulitAdapter extends RecyclerView.Adapter {

        private MulitBean mulitBean;
        private List<MulitGroup1> gruops1;
        private List<Cp> cpGruops;
        private List<String> cpGroupNames;
        private final int TYPE1 = 1, TYPE2 = 2, TYPE_TITLE = 3;
        private LayoutInflater inflater;
        private TextView alreadyAddTv;

        DishMulitAdapter(MulitBean mulitBean, Context context) {
            this.mulitBean = mulitBean;
            inflater = LayoutInflater.from(context);
        }

        /**
         * 用于最后获取整个整合的数据
         *
         * @return
         */
        public MulitBean getMulitBean() {
            return mulitBean;
        }

        public void setAlreadyAddTv(TextView alreadyAddTv) {
            this.alreadyAddTv = alreadyAddTv;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE1: {
                    View itemView = inflater.inflate(R.layout.item_dish_mulit_type1, parent, false);
                    return new Type1Holder(itemView);
                }
                case TYPE2: {
                    View itemView = inflater.inflate(R.layout.item_mulit_v2, parent, false);
                    return new Type2Holder(itemView);
                }
                case TYPE_TITLE: {
                    View itemView = inflater.inflate(R.layout.mulitgrouptitle, parent, false);
                    return new Type3Holder(itemView);
                }
            }
            return null;

        }

        class Type3Holder extends RecyclerView.ViewHolder {
            private TextView titleView;

            public Type3Holder(View itemView) {
                super(itemView);
                titleView = itemView.findViewById(R.id.title);
            }
        }

        class Type2Holder extends RecyclerView.ViewHolder {
            private TextView vegetablesName;
            private TextView vegetablesPrice;
            private DishAddReduceLayout mulit2AaddReduceLayout;

            public Type2Holder(View itemView) {
                super(itemView);
                vegetablesName = itemView.findViewById(R.id.vegetablesName);
                vegetablesPrice = itemView.findViewById(R.id.vegetablesPrice);
                mulit2AaddReduceLayout = itemView.findViewById(R.id.mulit2AaddReduceLayout);
            }
        }

        class Type1Holder extends RecyclerView.ViewHolder {
            private TextView typeTitleTv;
            private MallGridView multiV1Layout;

            public Type1Holder(View itemView) {
                super(itemView);
                typeTitleTv = itemView.findViewById(R.id.typeTitleTv);
                multiV1Layout = itemView.findViewById(R.id.multiV1Layout);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case TYPE1: {
                    assembleHolderType1(holder, position);
                }
                break;
                case TYPE2: {
                    assembleHolderType2(holder, position);
                }
                break;
                case TYPE_TITLE: {
                    assembleHolderTypeTitle(holder, position);
                }
                break;
            }
        }

        private void assembleHolderType1(RecyclerView.ViewHolder holder, int position) {
            Type1Holder type1Holder = (Type1Holder) holder;
            MulitGroup1 mulitGroup1 = gruops1.get(position);
            type1Holder.typeTitleTv.setText(mulitGroup1.getGroupName());
            List<String> groupValues = mulitGroup1.getGroupValues();
            MallGridView mallGridView = type1Holder.multiV1Layout;
            final int selectPosition = mulitGroup1.getSelectPosition();
            mallGridView.removeAllViews();
            for (int i = 0; i < groupValues.size(); i++) {
                TextView view = (TextView) inflater.inflate(R.layout.mulit_v1_tv, mallGridView, false);
                view.setText(groupValues.get(i));
                if (selectPosition == i) {
                    view.setSelected(true);
                } else {
                    view.clearFocus();
                    view.setSelected(false);
                }
                view.setTag(i);
                view.setOnClickListener(new MulitGroupClickListener(mulitGroup1));
                mallGridView.reset();
                mallGridView.addView(view);
            }
        }

        /**
         * 点击单个
         */
        private class MulitGroupClickListener implements View.OnClickListener {
            MulitGroup1 mulitGroup1;

            MulitGroupClickListener(MulitGroup1 mulitGroup1) {
                this.mulitGroup1 = mulitGroup1;
            }

            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                mulitGroup1.setSelectPosition(position);
                notifyDataSetChanged();
            }
        }

        /**
         * 组装菜品数据
         *
         * @param holder
         * @param position
         */
        private void assembleHolderType2(RecyclerView.ViewHolder holder, int position) {
            Type2Holder type2Holder = (Type2Holder) holder;
            position = position - gruops1.size() - 1;
            Cp cp = cpGruops.get(position);
            type2Holder.vegetablesName.setText(cp.getCpName());
            type2Holder.vegetablesPrice.setText(cp.getCpPrice());
            type2Holder.mulit2AaddReduceLayout.setNum(cp.getCpNum());
            type2Holder.mulit2AaddReduceLayout.setListener(new CpAddReduceListener(cp));
        }

        private class CpAddReduceListener implements DishAddReduceLayout.DishAddReduceListener {
            private Cp cp;

            CpAddReduceListener(Cp cp) {
                this.cp = cp;
            }

            @Override
            public void numChanged(int num, int position, boolean isAdd) {
                cp.setCpNum(num);
                notifyDataSetChanged();
                setAlshow(isAdd, cp.getCpName(), cp.getCpNum());
            }
        }

        StringBuffer stringBuffer;
        private boolean isNoneIn = true;//什么菜也没加

        private void setAlshow(boolean isAdd, String cpName, int cpNum) {
            StringBuffer tempStringBuffer = new StringBuffer();
            if (null == stringBuffer) {
                stringBuffer = new StringBuffer();
                stringBuffer.append(String.valueOf(alreadyAddTv.getText()));//已选择菜品数量：
                stringBuffer.append(cpName);
                stringBuffer.append("*");
                stringBuffer.append(String.valueOf(cpNum));
                //已选择菜品数量：平菇*3
                isNoneIn = false;
            } else {
                String alreadyHaveStr = String.valueOf(stringBuffer);
                LogUtil.i(MulitDialog.this.getClass(), alreadyHaveStr);
                if (isAdd) {//添加
                    if (alreadyHaveStr.contains(cpName)) {
                        //包含的时候在数量上添加
                        if (alreadyHaveStr.contains("、")) {
//                            已选择菜品数量：平菇*3、香菇*5
                            int lastDHPosition = stringBuffer.lastIndexOf("、");
                            int lastCurStrPosition = stringBuffer.indexOf(cpName);
                            if (lastCurStrPosition > lastDHPosition) {
                                //在最后的顿号后面
                                stringBuffer = stringBuffer.replace(stringBuffer.indexOf(cpName) + cpName.length() + 1, stringBuffer.length(), String.valueOf(cpNum));
                            } else {
                                String str1 = stringBuffer.substring(0, stringBuffer.indexOf(cpName));
                                String str2 = stringBuffer.substring(stringBuffer.indexOf(cpName), stringBuffer.length());
                                //对str2进行处理
                                StringBuffer tempBuffer = new StringBuffer(str2);
                                tempBuffer = tempBuffer.replace(tempBuffer.indexOf(cpName) + cpName.length() + 1, tempBuffer.indexOf("、"), String.valueOf(cpNum));
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(str1);
                                stringBuffer.append(tempBuffer);
                            }
                        } else {
//                            已选择菜品数量：平菇*1
                            stringBuffer = stringBuffer.replace(stringBuffer.indexOf(cpName) + cpName.length() + 1, stringBuffer.length(), String.valueOf(cpNum));
                            //取得数量
//                            数量加菜品数量
                        }
                    } else {//不包含追加
                        // 已选择菜品数量：平菇*3
                        if (!isNoneIn)
                            stringBuffer.append("、");
                        stringBuffer.append(cpName);
                        stringBuffer.append("*");
                        stringBuffer.append(String.valueOf(cpNum));
                        //  已选择菜品数量：平菇*3、香菇*5
                    }
                    isNoneIn = false;
                } else {//减少逻辑
                    if (cpNum == 0) {
                        String str1 = stringBuffer.substring(0, stringBuffer.indexOf(cpName));
                        String str2 = stringBuffer.substring(stringBuffer.indexOf(cpName), stringBuffer.length());
                        StringBuffer tempBuffer = new StringBuffer(str2);
                        if (str2.contains("、")) {//包含顿号
                            int lastDHPosition = stringBuffer.lastIndexOf("、");
                            int lastCurStrPosition = stringBuffer.indexOf(cpName);
                            if (lastCurStrPosition > lastDHPosition) {
                                //在最后的顿号后面
                                stringBuffer = new StringBuffer();
                                str1 = str1.substring(0, str1.length() - 1);
                                stringBuffer.append(str1);
                            } else {
                                String str3 = tempBuffer.substring(str2.indexOf("、") + 1, tempBuffer.length());
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(str1);
                                stringBuffer.append(str3);
                            }
                        } else {//不包含顿号
                            if (str1.contains("、")) {
                                str1 = str1.substring(0, str1.lastIndexOf("、"));
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(str1);
                            } else {
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(str1);
                                isNoneIn = true;
                            }

                        }
                    } else {
                        int lastDHPosition = stringBuffer.lastIndexOf("、");
                        int lastCurStrPosition = stringBuffer.indexOf(cpName);
                        if (lastCurStrPosition > lastDHPosition) {
                            //在最后的顿号后面
                            stringBuffer = stringBuffer.replace(stringBuffer.indexOf(cpName) + cpName.length() + 1, stringBuffer.length(), String.valueOf(cpNum));
                        } else {
                            String str1 = stringBuffer.substring(0, stringBuffer.indexOf(cpName));
                            String str2 = stringBuffer.substring(stringBuffer.indexOf(cpName), stringBuffer.length());
                            //对str2进行处理
                            StringBuffer tempBuffer = new StringBuffer(str2);
                            tempBuffer = tempBuffer.replace(tempBuffer.indexOf(cpName) + cpName.length() + 1, tempBuffer.indexOf("、"), String.valueOf(cpNum));
                            stringBuffer = new StringBuffer();
                            stringBuffer.append(str1);
                            stringBuffer.append(tempBuffer);
                        }

                    }

                }
            }
            alreadyAddTv.setText(stringBuffer);

        }

        private void assembleHolderTypeTitle(RecyclerView.ViewHolder holder, int position) {
            Type3Holder typeHolder = (Type3Holder) holder;
            position = position - gruops1.size();
            typeHolder.titleView.setText(cpGroupNames.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            int type = TYPE1;
            if (position < gruops1.size()) {
                type = TYPE1;
            } else if (position == gruops1.size()) {
                type = TYPE_TITLE;
            } else if (position > gruops1.size()) {
                type = TYPE2;
            }
            return type;
        }

        @Override
        public int getItemCount() {
            return gruops1.size() + cpGruops.size() + 1;
        }

        public void analysisData(MulitBean mulitBean) {
            List<MulitBaseGroup> list = mulitBean.getMulitBaseGroups();
            gruops1 = new ArrayList<>();
            cpGruops = new ArrayList<>();
            cpGroupNames = new ArrayList<>();
            for (MulitBaseGroup mulitBaseGroup : list) {
                if (mulitBaseGroup.getGroupType().equals("type1")) {
                    MulitGroup1 mulitGruop1 = (MulitGroup1) mulitBaseGroup;
                    gruops1.add(mulitGruop1);
                } else if (mulitBaseGroup.getGroupType().equals("type2")) {
                    MulitGroup2 mulitGruop2 = (MulitGroup2) mulitBaseGroup;
                    cpGroupNames.add(mulitGruop2.getGroupName());
                    for (Cp cp : mulitGruop2.getCps()
                            ) {
                        cpGruops.add(cp);
                    }
                }
            }
        }
    }

    private void changeTitleClick(LinearLayout layoutTitle, int position) {
        tpeZfPostion = position;
        layoutTitle.getChildAt(tpeZfPostion).setSelected(true);
        for (int i = 0; i < layoutTitle.getChildCount(); i++) {
            TextView textView = (TextView) layoutTitle.getChildAt(i);
            if (i != tpeZfPostion) {
                textView.clearFocus();
                textView.setSelected(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choiceOverTv: {
                for (int i = 0; i < mulitBeans.size(); i++) {
                    mulitDialogListener.addShoppingCat(mulitBeans.get(i));
                }
                dismiss();
            }
            break;
        }
    }

    private void restData() {
        mulitBeans.clear();
    }

    /**
     * 数据模拟
     */
    public MulitBean analogData(DishBean dishBean) {
        MulitBean mulitBean = new MulitBean();
        mulitBean.setName(dishBean.getName());
        mulitBean.setNum(dishBean.getShopNum());
        mulitBean.setTotalPrice(Double.parseDouble(dishBean.getSinglePrice()));
        List<MulitBaseGroup> list = new ArrayList<>();

        MulitGroup1 zfGruop = new MulitGroup1();
        zfGruop.setGroupType("type0");
        zfGruop.setGroupName("做法");
        List<String> zfValues = new ArrayList<>();
        zfValues.add("炒");
        zfValues.add("蒸");
        zfValues.add("炸");
        zfGruop.setGroupValues(zfValues);
        zfGruop.setSelectPosition(0);
        list.add(zfGruop);

        for (int i = 0; i < 5; i++) {
            MulitGroup1 wdGruop = new MulitGroup1();
            wdGruop.setGroupType("type1");
            wdGruop.setGroupName("味道");
            List<String> wdValues = new ArrayList<>();
            wdValues.add("贼辣");
            wdValues.add("特辣");
            wdValues.add("中辣");
            wdValues.add("微辣");
            wdValues.add("甜辣");
            wdValues.add("舔不辣");
            wdValues.add("蒜香");
            wdValues.add("酱香");
            wdGruop.setGroupValues(wdValues);
            list.add(wdGruop);
        }
        MulitGroup2 jcGroup = new MulitGroup2();

        jcGroup.setGroupType("type2");
        jcGroup.setGroupName("加菜");
        List<Cp> cps = new ArrayList<>();
        Cp cp = new Cp();
        cp.setCpName("金针菇");
        cp.setCpNum(0);
        cp.setCpPrice("￥10.00");
        cps.add(cp);

        Cp cp1 = new Cp();
        cp1.setCpName("平菇");
        cp1.setCpNum(0);
        cp1.setCpPrice("￥5.00");
        cps.add(cp1);

        Cp cp2 = new Cp();
        cp2.setCpName("香菇");
        cp2.setCpNum(0);
        cp2.setCpPrice("￥55.00");
        cps.add(cp2);


        Cp cp3 = new Cp();
        cp3.setCpName("香菜");
        cp3.setCpNum(0);
        cp3.setCpPrice("￥55.00");
        cps.add(cp3);

        jcGroup.setCps(cps);
        list.add(jcGroup);
        mulitBean.setMulitBaseGroups(list);
        return mulitBean;
    }

    public RecyclerView getDishRecycleView() {
        return dishRecycleView;
    }
}
