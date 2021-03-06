package com.demo.fish.app.main.myOrder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.fish.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderFragment extends Fragment {
    private ExpandableListView elMainOrdercenter;
    private Map<String,List<OrderInfo>> dataMap;
    private String[] titleArr;
    private int[] iconArr=new int[]{R.mipmap.icon1,R.mipmap.icon2,R.mipmap.icon3};
    private MyAdapter myAdapter;

    public MyOrderFragment() {
        // Required empty public constructor
    }

    public static MyOrderFragment newInstance() {
        MyOrderFragment fragment = new MyOrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_order, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
        //初始化点击监听事件
        initOnclickListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    /**
     * 初始化数据
     */
    private void initData(){
        //初始化ExpandlistView的id
        elMainOrdercenter=(ExpandableListView)getActivity().findViewById(R.id.el_main_ordercenter);
        //初始化列表数据，正常由服务器返回的Json数据
        initJsonData();
        myAdapter=new MyOrderFragment.MyAdapter();
        elMainOrdercenter.setAdapter(myAdapter);
        //设置列表展开
        for(int i=0;i<dataMap.size();i++){
            elMainOrdercenter.expandGroup(i);
        }
    }
    /**
     * 初始点击事件
     */
    private void initOnclickListener(){
        //设置父标题点击不能收缩
        elMainOrdercenter.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        //订单子条目的点击事件
        elMainOrdercenter.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

    /**
     * ExpandableListviewAdapter初始化
     */
    private class MyAdapter extends BaseExpandableListAdapter{
        //  获得父项的数量
        @Override
        public int getGroupCount() {
            return dataMap.size();
        }
        //  获得某个父项的子项数目
        @Override
        public int getChildrenCount(int groupPosition) {
            return dataMap.get(titleArr[groupPosition]).size();
        }
        //  获得某个父项
        @Override
        public Object getGroup(int groupPosition) {
            return dataMap.get(titleArr[groupPosition]);
        }
        //  获得某个父项的某个子项
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return dataMap.get(titleArr[groupPosition]).get(childPosition);
        }
        //  获得某个父项的id
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        //  获得某个父项的某个子项的id
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
        @Override
        public boolean hasStableIds() {
            return false;
        }
        //  获得父项显示的view
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=View.inflate(getContext(),R.layout.parent_view,null);
            }
            ImageView ivParentviewIcon=(ImageView) convertView.findViewById(R.id.iv_parentview_icon);
            TextView tvParentviewTitle=(TextView) convertView.findViewById(R.id.tv_parentview_title);
            ivParentviewIcon.setImageResource(iconArr[groupPosition]);
            tvParentviewTitle.setText(titleArr[groupPosition]);
            return convertView;
        }
        //  获得子项显示的view
        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=View.inflate(getContext(),R.layout.child_view,null);
            }
            //获取布局控件id
            ImageView img=(ImageView) convertView.findViewById(R.id.img);
            img.setImageResource(dataMap.get(titleArr[groupPosition]).get(childPosition).getImg());
            TextView tvChildviewContent=(TextView) convertView.findViewById(R.id.tv_childview_content);
            tvChildviewContent.setText(dataMap.get(titleArr[groupPosition]).get(childPosition).getName());
            Button btnChildviewDelete=(Button) convertView.findViewById(R.id.btn_childview_delete);
            Button btnChildviewEvaluate=(Button) convertView.findViewById(R.id.btn_childview_evaluate);
            //根据服务器返回的数据来显示和隐藏按钮
            final OrderInfo orderInfo=dataMap.get(titleArr[groupPosition]).get(childPosition);
            if(orderInfo.isEvaluateState()){
                btnChildviewEvaluate.setVisibility(View.VISIBLE);
            }else {
                btnChildviewEvaluate.setVisibility(View.GONE);
            }
            if(orderInfo.isDeleteState()){
                btnChildviewDelete.setVisibility(View.VISIBLE);
            }else {
                btnChildviewDelete.setVisibility(View.GONE);
            }
            //设置评价按钮的点击事件
            btnChildviewEvaluate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"跳转到"+orderInfo.getName()+"的评价页面",Toast.LENGTH_SHORT).show();
                }
            });
            //设置删除按钮的点击事件
            btnChildviewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataMap.get(titleArr[groupPosition]).remove(childPosition);
                    myAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
        //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
    /**
     * 初始化列表数据，正常由服务器返回的Json数据
     */
    private void initJsonData(){
        dataMap=new HashMap<String,List<OrderInfo>>();
        titleArr=new String[]{"朱雨婷","代继桥","刘青羽"};
        List<OrderInfo> list1=new ArrayList<OrderInfo>();
        List<OrderInfo> list2=new ArrayList<OrderInfo>();
        List<OrderInfo> list3=new ArrayList<OrderInfo>();
        OrderInfo orderInfo1=new OrderInfo();
        orderInfo1.setName("二手iphone7");
        orderInfo1.setIcon(R.drawable.shouji1);
        orderInfo1.setEvaluateState(true);
        orderInfo1.setDeleteState(true);
        OrderInfo orderInfo2=new OrderInfo();
        orderInfo2.setName("二手iphoneX");
        orderInfo2.setIcon(R.drawable.shouji8);
        orderInfo2.setEvaluateState(false);
        orderInfo2.setDeleteState(true);

        OrderInfo orderInfo4=new OrderInfo();
        orderInfo4.setName("二手kindle 9成新");
        orderInfo4.setIcon(R.drawable.kindle2);
        orderInfo4.setEvaluateState(true);
        orderInfo4.setDeleteState(false);
        OrderInfo orderInfo5=new OrderInfo();
        orderInfo5.setName("二手kindle 95新");
        orderInfo5.setIcon(R.drawable.kindle8);
        orderInfo5.setEvaluateState(false);
        orderInfo5.setDeleteState(true);

        OrderInfo orderInfo6=new OrderInfo();
        orderInfo6.setName("二手书籍");
        orderInfo6.setIcon(R.drawable.shuji1);
        orderInfo6.setEvaluateState(true);
        orderInfo6.setDeleteState(true);
        OrderInfo orderInfo7=new OrderInfo();
        orderInfo7.setName("二手书籍");
        orderInfo7.setIcon(R.drawable.shuji8);
        orderInfo7.setEvaluateState(false);
        orderInfo7.setDeleteState(true);

        list1.add(orderInfo1);
        list1.add(orderInfo2);
        list2.add(orderInfo4);
        list2.add(orderInfo5);
        list3.add(orderInfo6);
        list3.add(orderInfo7);
        dataMap.put(titleArr[0],list1);
        dataMap.put(titleArr[1],list2);
        dataMap.put(titleArr[2],list3);
    }

}
