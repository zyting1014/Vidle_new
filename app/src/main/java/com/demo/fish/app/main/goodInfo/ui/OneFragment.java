package com.demo.fish.app.main.goodInfo.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demo.fish.R;
import com.demo.fish.app.main.goodInfo.utils.MyScrollView;
import com.demo.fish.app.main.goodInfo.utils.PullUpToLoadMore;


public class OneFragment extends Fragment {
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_one, container, false);
        initView();
        return mView;
    }

    private void initView() {
        MyScrollView oneScrollView= (MyScrollView) mView.findViewById(R.id.oneScrollview);
        oneScrollView.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollToBottom() {

            }

            @Override
            public void onScrollToTop() {

            }

            @Override
            public void onScroll(int scrollY) {
                if (scrollY == 0) {
                    PullUpToLoadMore.isTop= true;
                } else {
                    PullUpToLoadMore.isTop= false;
                }
            }

            @Override
            public void notBottom() {

            }

        });
    }
}
