package com.huaan.titlebar;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.library.titlebarlibrary.TitleBar;

public class MainActivity extends AppCompatActivity {

    private TitleBar mTitleBar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.startImmersive(this,mDrawerLayout);
        //点击事件
        mTitleBar.setOnTitleBarClickListener(new TitleBar.OnTitleBarClickListener() {
            @Override
            public void onLeftBtnClick(View view) {

            }

            @Override
            public void onRightBtnClick(View view) {

            }

            @Override
            public void onTitleClick(View view) {

            }
        });

//        或则
        mTitleBar.getImg_left().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
}
