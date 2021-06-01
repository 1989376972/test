package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1= findViewById(R.id.button_1);
        username=findViewById(R.id.editTextTextPersonName);
        password=findViewById(R.id.editTextNumberPassword);
        //点击登录时//
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name=username.getText().toString();//将信息化为string类型//
                String pass=password.getText().toString();

                if (DateUser.getInstance().login(name,pass)){//判断账号密码//
                    Intent intent=new Intent(MainActivity.this,wanandriod.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"密码失败，请重试",Toast.LENGTH_LONG).show();

                }
            }
        });
        Button button2= findViewById(R.id.button_2);//进入注册界面//
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}