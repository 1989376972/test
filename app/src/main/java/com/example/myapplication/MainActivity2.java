package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

//注册页面
public class MainActivity2 extends AppCompatActivity {
    private EditText uername;
    private EditText password;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        uername = (EditText) findViewById(R.id.editTextTextPersonName2);//获得信息//
        password=(EditText) findViewById(R.id.editTextTextPassword);
        password2=(EditText) findViewById(R.id.editTextTextPassword2);
        Button button2=(Button) findViewById(R.id.button2);
        Button button3=(Button) findViewById(R.id.button3);
        button2.setOnClickListener(new Registerbutton());
        button3.setOnClickListener(new Registerbutton());
    }
    public class Registerbutton implements View.OnClickListener{
        public void onClick(View v){
            String name=uername.getText().toString();//将信息化为string类型//
            String pass=password.getText().toString();
            String passlength[]=pass.split("");//用于判断密码长度//
            String pass2=password2.getText().toString().trim();
            switch (v.getId()){
                case R.id.button2://判断能否注册 信息输入格式//
                    if(name.toString().equals("")||pass.toString().equals("")){
                        Toast.makeText(MainActivity2.this,"各项不能为空",Toast.LENGTH_LONG).show();
                    }
                    else if (passlength.length<6){
                        Toast.makeText(MainActivity2.this,"密码过短，请重新输入",Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(pass.toString().equals(pass2)){
                            //register(name,pass,pass2);//
                            DateUser.getInstance().register(name,pass);
                            Toast.makeText(MainActivity2.this,"注册成功，请返回登录",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity2.this,"两次输入密码不一致",Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                case R.id.button3://返回登录界面//
                    Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
    /*
    public void register(String name,String pass,String pass2){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                    URL url= new URL("https://www.wanandroid.com/user/register");
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("username=name&password=pass&repassword=pass2");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
     */
}