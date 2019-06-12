package com.example.zafkiel.mymusicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zafkiel.EditActivity;
import com.example.zafkiel.MainActivity;
import com.example.zafkiel.R;
import com.example.zafkiel.mymusicplayer.LogicFragment;
import com.example.zafkiel.mymusicplayer.MusicPagerAdapter;

import java.util.ArrayList;
import java.util.List;

//实现OnClickListener的接口
public class MainActivity_music extends MainActivity {
    //定义activity_main.xml的控件对象
    private TextView logicTv;
    private ViewPager viewPager;
    //将Fragment放入List集合中，存放fragment对象
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        //绑定id
        bangdingID();
        //设置监听
        jianting();
        //创建fragment对象
        LogicFragment logicFragment = new LogicFragment();
        //将fragment对象添加到fragmentList中
        fragmentList.add(logicFragment);
        //通过MusicPagerAdapter类创建musicPagerAdapter的适配器，下面我将添加MusicPagerAdapter类的创建方法
        MusicPagerAdapter musicPagerAdapter = new MusicPagerAdapter(getSupportFragmentManager(), fragmentList);
        //viewPager绑定适配器
        viewPager.setAdapter(musicPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        logicTv.setTextColor(getResources().getColor(R.color.colorAccent));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void jianting() {
        logicTv.setOnClickListener(this);
    }

    private void bangdingID() {
        logicTv = findViewById(R.id.main_logic_tv);
        viewPager = findViewById(R.id.main_vp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_logic_tv:
                //实现点击TextView切换fragment
                viewPager.setCurrentItem(0);
                break;
            default:
                break;
        }

    }
}

