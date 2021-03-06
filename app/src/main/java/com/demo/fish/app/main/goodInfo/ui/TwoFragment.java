package com.demo.fish.app.main.goodInfo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.demo.fish.R;

import com.demo.fish.app.main.goodInfo.adapter.RecyclerAdapter;
import com.demo.fish.app.main.goodInfo.utils.PullUpToLoadMore;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    View mView;
    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;
    ArrayList<String> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] comment={"朱雨婷：\n非常好，很新，很nice！！！值得购买！！！","代继桥：\n快递很给力，好评好评！！！",
        "刘青羽：\n此用户没有填写评论!"};
        for (int i = 0; i < 20; i++) {
            mList.add(comment[i%3]);
        }
        mView = inflater.inflate(R.layout.fragment_two, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerAdapter = new RecyclerAdapter(getActivity());
        mRecyclerAdapter.setList(mList);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //兼容api23以以下版本
        mRecyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                //得到当前界面，第一个子视图的position
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0) {
                    PullUpToLoadMore.isTop = true;
                } else {
                    PullUpToLoadMore.isTop = false;
                }
            }
        });


//        MyScrollView twoScrollView = (MyScrollView) mView.findViewById(R.id.twoScrollview);
//        twoScrollView.setScrollListener(new MyScrollView.ScrollListener() {
//            @Override
//            public void onScrollToBottom() {
//
//            }
//
//            @Override
//            public void onScrollToTop() {
//
//            }
//
//            @Override
//            public void onScroll(int scrollY) {
//                if (scrollY == 0) {
//                    PullUpToLoadMore.isTop  = true;
//                } else {
//                    PullUpToLoadMore.isTop  = false;
//                }
//            }
//
//            @Override
//            public void notBottom() {
//
//            }
//
//        });
    }

}
