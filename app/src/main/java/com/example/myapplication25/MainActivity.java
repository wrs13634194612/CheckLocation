package com.example.myapplication25;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnOptionsSelectListener, OnOptionsSelectChangeListener {
    //参考网址：https://github.com/Bigkoo/Android-PickerView
    //  省
    protected List<String> options1Items = new ArrayList<>();
    protected List<String> city;
    //  市
    protected List<List<String>> options2Items = new ArrayList<>();
    //  区
    protected List<List<List<String>>> options3Items = new ArrayList<>();
    //  省地理
    protected List<String> options1Itemsnumber = new ArrayList<>();
    protected List<String> citynumber;
    protected List<List<String>> area;
    protected List<List<String>> areanumber;
    protected List<String> chirendenarea;
    protected List<String> chirendenareanumber;
    //  市地理
    protected List<List<String>> options2Itemsnumber = new ArrayList<>();
    //  区地理
    protected List<List<List<String>>> options3Itemsnumber = new ArrayList<>();

    private Button btn_location;
    private TextView tv_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCaseInfo2();
        btn_location = findViewById(R.id.btn_location);
        tv_location = findViewById(R.id.tv_location);
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anXzXz();
            }
        });
    }

    public void anXzXz() {
        OptionsPickerView build = SelectPickerUtil.initChoiceArea(MainActivity.this, this, this);
        build.setPicker(options1Items, options2Items, options3Items);//添加数据源
        build.show();
    }

    private void getCaseInfo2() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("citydata.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //  Log.e("TAG","loadJsonFromAssests_all:"+json.toString());
        Gson gson = new Gson();
        CityBean userSimple = gson.fromJson(json, CityBean.class);
        for (int i = 0; i < userSimple.getDatas().getList().size(); i++) {
            options1Items.add(userSimple.getDatas().getList().get(i).getArea_name());
            options1Itemsnumber.add(userSimple.getDatas().getList().get(i).getArea_id());
            city = new ArrayList<>();
            citynumber = new ArrayList<>();
            area = new ArrayList<>();
            areanumber = new ArrayList<>();
            List<CityBean.DatasBean.ListBean.CitylistBean> citylist = userSimple.getDatas().getList().get(i).getCitylist();
            for (int o = 0; o < userSimple.getDatas().getList().get(i).getCitylist().size(); o++) {
                city.add(citylist.get(o).getArea_name());
                citynumber.add(citylist.get(o).getArea_id());
                chirendenarea = new ArrayList<>();
                chirendenareanumber = new ArrayList<>();
                List<CityBean.DatasBean.ListBean.CitylistBean.ArealistBean> arealist = userSimple.getDatas().getList().get(i).getCitylist().get(o).getArealist();
                for (int u = 0; u < userSimple.getDatas().getList().get(i).getCitylist().get(o).getArealist().size(); u++) {
                    chirendenarea.add(arealist.get(u).getArea_name());
                    chirendenareanumber.add(arealist.get(u).getArea_id());
                }
                area.add(chirendenarea);
                areanumber.add(chirendenareanumber);
            }
            options2Items.add(city);
            options2Itemsnumber.add(citynumber);
            options3Items.add(area);
            options3Itemsnumber.add(areanumber);
        }
    }

    @Override
    public void onOptionsSelectChanged(int options1, int options2, int options3) {

    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View view) {
        tv_location.setText(String.format("%s省--%s--%s",
                options1Items.get(options1),
                options2Items.get(options1).get(options2),
                options3Items.get(options1).get(options2).get(options3)));
        Log.e("TAG", "onOptionsSelect_end:" +
                options1Itemsnumber.get(options1) + options1Items.get(options1)
                + options2Itemsnumber.get(options1).get(options2) + options2Items.get(options1).get(options2)
                + options3Itemsnumber.get(options1).get(options2).get(options3) + options3Items.get(options1).get(options2).get(options3));
    }
}
