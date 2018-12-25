package com.demo.fish.app.main.goodInfo.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.fish.R;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.demo.fish.app.main.goodInfo.adapter.NetworkImageHolderView;
import com.demo.fish.app.main.goodInfo.adapter.SimpleFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoodInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Fragment> list_fragment = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();
    private OneFragment mOneFragment;
    private TwoFragment mTwoFragment;
    private TextView shoppingCart;

    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;
    ConvenientBanner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_good_info);

        initControls();
    }


    /**
     * 初始化各控件
     */
    private void initControls() {
        List<String> networkImages = new ArrayList<>();
        networkImages.add("https://img.alicdn.com/imgextra/i2/2099020602/TB2Tq6pA9tYBeNjSspaXXaOOFXa_!!2099020602.jpg");
        networkImages.add("https://img.alicdn.com/imgextra/i4/2099020602/TB2OXuPsyCYBuNkHFCcXXcHtVXa_!!2099020602.jpg");
        networkImages.add("https://img.alicdn.com/imgextra/i2/2099020602/TB28ayFA7SWBuNjSszdXXbeSpXa_!!2099020602.jpg");
        networkImages.add("https://img.alicdn.com/imgextra/i1/2099020602/TB20gNpsS8YBeNkSnb4XXaevFXa_!!2099020602.jpg");
        mBanner = (ConvenientBanner) findViewById(R.id.banner_convenient);
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages);
        mBanner.setPointViewVisible(true);//是否显示小圆点
        mBanner.startTurning(2000);


        //初始化各fragment
        mOneFragment = new OneFragment();
        mTwoFragment = new TwoFragment();
        list_fragment.add(mOneFragment);
        list_fragment.add(mTwoFragment);
        list_title.add("商品详情");
        list_title.add("商品评价");


        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, list_fragment, list_title);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        shoppingCart = (TextView) findViewById(R.id.shoppingcat);
        shoppingCart.setOnClickListener(this);
        findViewById(R.id.communicate).setOnClickListener(this);
        findViewById(R.id.not_collect_tv).setOnClickListener(this);
        findViewById(R.id.buy_immediately_bt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shoppingcat:
                Toast.makeText(getApplicationContext(), "添加成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy_immediately_bt:
                TextView price = (TextView) findViewById(R.id.price);
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setMessage("总共需支付" + price.getText() + "元，您确定要下单吗？");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "支付成功！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.show();
                break;
            case R.id.not_collect_tv:
                Toast.makeText(getApplicationContext(), "收藏成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.communicate:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("18514520601"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
    }
    }
}
