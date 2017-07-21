package com.zhuyong.postmanloading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    PostManLoadingView postManLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postManLoadingView = (PostManLoadingView) findViewById(R.id.pml_view);
        postManLoadingView.start();
    }
}
