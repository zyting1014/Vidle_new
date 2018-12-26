package com.demo.fish.welcomeGuide;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.fish.R;
import com.demo.fish.app.main.entity.Person;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_main_title;//标题
    private TextView tv_back;//返回按钮
    private Button btn_register;//注册按钮
    private EditText et_user_name,et_psw,et_psw_again;//用户名，密码，再次输入的密码的控件
    private String userName,psw,pswAgain;//用户名，密码，再次输入的密码的控件的获取值
    private RelativeLayout rl_title_bar;//标题布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置此界面为竖屏
        init();
    }

    private void init(){
        tv_main_title=(TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");//titlebar赋值
        tv_back=(TextView) findViewById(R.id.tv_back);//返回按钮
        rl_title_bar=(RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);//从activity_register.xml 页面中获取对应的UI控件
        btn_register=(Button)findViewById(R.id.btn_register);
        et_user_name=(EditText) findViewById(R.id.et_user_name);
        et_psw=(EditText) findViewById(R.id.et_psw);
        et_psw_again=(EditText) findViewById(R.id.et_psw_again);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在相应控件中的字符串
                userName=et_user_name.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                pswAgain=et_psw_again.getText().toString().trim();

                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }

                //判断该用户是否存在
                BmobQuery<Person> query = new BmobQuery<>();//初始化查询
                query.addWhereEqualTo("id",userName);//查询id等于username的数据
                query.findObjects(new FindListener<Person>() {
                    @Override
                    public void done(List<Person> list, BmobException e) {
                        if(list.size()!=0){
                            Toast.makeText(RegisterActivity.this, "该用户已存在！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

                //注册该用户
                Person user = new Person();
                user.setUsername(userName);
                user.setPassword(psw);
                user.signUp(new SaveListener<Person>() {
                    @Override
                    public void done(Person s, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            RegisterActivity.this.finish();//销毁注册界面
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));//跳转到登陆界面
                        }else{
                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                user.setBalance(0);
            }
        });
    }
}
