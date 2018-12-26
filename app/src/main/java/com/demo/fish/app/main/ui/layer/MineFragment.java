package com.demo.fish.app.main.ui.layer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.fish.R;
import com.demo.fish.app.main.entity.Person;
import com.squareup.picasso.Picasso;

import cn.bmob.v3.BmobUser;

public class MineFragment extends Fragment {
    private TextView tname;
    private String name;
    private TextView tbalance;
    private int balance;
    private Person person;

    private ImageView imageView;
    private String url;
    public MineFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();

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
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        person = BmobUser.getCurrentUser(Person.class);
        name = (String) BmobUser.getObjectByKey("username");
        tname = view.findViewById(R.id.textview_43);
        tname.setText(name);

        balance = (int)BmobUser.getObjectByKey("balance");
        tbalance = view.findViewById(R.id.textview_45);
        tbalance.setText(balance+"");

        imageView = view.findViewById(R.id.imgview_profile);

        url = person.getProfile_photo();
        Picasso.with(getContext())
                .load(url)
                .into(imageView);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
