package com.demo.fish.app.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.fish.R;
import com.demo.fish.app.main.PostGoods.PostGoodsActivity;
import com.demo.fish.app.main.ui.layer.MainViewLayer;
import com.demo.fish.app.main.model.IMainViewModel;
import com.demo.fish.app.main.model.impl.MainViewModel;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private IMainViewModel mViewModel;
    private ImageView ic_tab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.acitonbar, null);
        Toolbar actionBar=view.findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        mViewModel = new MainViewModel(new MainViewLayer(this));
        mViewModel.bind();
        mViewModel.loadDrawerItemData();
        mViewModel.setIndex(MainViewLayer.TAB_INDEX_HOME);
        ic_tab_add= (ImageView) findViewById(R.id.dd5);
        ic_tab_add.setOnClickListener(this);
    }



    @Override
    protected void onDestroy() {
        mViewModel.unbind();
        super.onDestroy();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.home) {
            mViewModel.setIndex(MainViewLayer.TAB_INDEX_HOME);
        } else if (v.getId() == R.id.find) {
            mViewModel.setIndex(MainViewLayer.TAB_INDEX_FIND);
        } else if (v.getId() == R.id.message) {
            mViewModel.setIndex(MainViewLayer.TAB_INDEX_MESSAGE);
        } else if (v.getId() == R.id.mine) {
            mViewModel.setIndex(MainViewLayer.TAB_INDEX_MINE);
        }
        else {
            mPopupWindow mPopupWindow =new mPopupWindow(MainActivity.this, new mPopupWindow.OnPopWindowClickListener() {
                @Override
                public void onPopWindowClickListener(View view) {
                    switch (view.getId()){
                        case R.id.iv_push_photo:
                            Toast.makeText(MainActivity.this,"点击了相机",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.iv_push_resale:
                            Toast.makeText(MainActivity.this,"点击了发布商品",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,PostGoodsActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });
            mPopupWindow.show();
        }
    }
}
