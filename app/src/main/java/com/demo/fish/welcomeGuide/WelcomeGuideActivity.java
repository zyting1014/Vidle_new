package com.demo.fish.welcomeGuide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import cn.bmob.v3.Bmob;
import com.demo.fish.R;
import com.demo.fish.app.main.ui.MainActivity;


public class WelcomeGuideActivity extends FragmentActivity {
    private static final int[] resource = new int[] { R.drawable.b1,
            R.drawable.b2, R.drawable.b3, R.drawable.b4 };
    private static final String TAG = WelcomeGuideActivity.class.getSimpleName();
    private Button btn_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "819cdd0bbb2ba2ff403ccb1a54dd386b");//初始化bmob
        setContentView(R.layout.activity_welcome_guide);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeGuideActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        MyFragmentStatePager adpter = new MyFragmentStatePager(
                getSupportFragmentManager());
        ColorAnimationView colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adpter);
        colorAnimationView.setmViewPager(viewPager, resource.length);
        colorAnimationView
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position,
                                               float positionOffset, int positionOffsetPixels) {
                        Log.e("TAG", "onPageScrolled");
                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (position == 3) {
                            btn_go.setVisibility(View.VISIBLE);
                        } else {
                            btn_go.setVisibility(View.GONE);
                        }
                        Log.e("TAG", "onPageSelected");
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        Log.e("TAG", "onPageScrollStateChanged");
                    }
                });
    }

    public class MyFragmentStatePager extends FragmentStatePagerAdapter {

        public MyFragmentStatePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(position);
        }

        @Override
        public int getCount() {
            return resource.length;
        }
    }

    @SuppressLint("ValidFragment")
    public static class MyFragment extends Fragment {
        private int position;

        public MyFragment(int position) {
            this.position = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resource[position]);
            return imageView;
        }
    }
}
