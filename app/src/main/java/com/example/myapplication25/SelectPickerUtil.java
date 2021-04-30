package com.example.myapplication25;


import android.app.Activity;
import android.graphics.Color;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

public class SelectPickerUtil {
    protected static OptionsPickerView pvOptions;


    public static OptionsPickerBuilder getSelectPickerBuilder2(OptionsPickerBuilder optionsPickerBuilder) {
        optionsPickerBuilder
                .setTitleText("title")
                .setSubmitColor(Color.parseColor("#11DBFF"))
                .setCancelColor(Color.parseColor("#999999"))
                .setDividerColor(Color.GRAY)
                .setLineSpacingMultiplier(2.2f)
                .setTextColorCenter(Color.parseColor("#11DBFF")) //设置选中项文字颜色
                .setContentTextSize(16);
        return optionsPickerBuilder;
    }

    public static OptionsPickerBuilder getSelectPickerBuilder3(OptionsPickerBuilder optionsPickerBuilder) {
        optionsPickerBuilder
                .setDividerColor(Color.DKGRAY)
                .setContentTextSize(20)
                .setSubmitColor(Color.parseColor("#11DBFF"))
                .setCancelColor(Color.parseColor("#999999"))
                //                .setDecorView(mFrameLayout)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setOutSideColor(0x00000000)
                .setLineSpacingMultiplier(2.2f)
                .setOutSideCancelable(true);
        return optionsPickerBuilder;
    }

    public static OptionsPickerView initChoiceArea(Activity activity, OnOptionsSelectListener onOptionsSelectListener, OnOptionsSelectChangeListener onOptionsSelectChangeListener) {
        pvOptions = new OptionsPickerBuilder(activity, onOptionsSelectListener)
                .setOptionsSelectChangeListener(onOptionsSelectChangeListener)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(0xFFF9731E)//标题文字颜色
                .setSubmitColor(0xFFF9731E)//确定按钮文字颜色
                .setCancelColor(0xFFF9731E)//取消按钮文字颜色
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
//                .isDialog(true)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();
        return pvOptions;
    }

}
