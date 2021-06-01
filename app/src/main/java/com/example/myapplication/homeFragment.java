package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.HttpConnection;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment {
    private  RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Data data;
    private rvAdapter myadapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//刷新操作
    private void refresh(){
        refreshLayout.setEnabled(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //添加新的数据
                artical newartical=new artical();
                newartical.uersername="wo";
                newartical.title="我是新添加的文章";
                newartical.nicedata="刚刚";
                data.data.datas.add(0,newartical);//将数据放在列表第一个，不然会添加到最后
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //停止刷新，并更新列表
                        myadapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);//改变状态
                    }
                },3000);
            }
        });
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //开一个线程进行网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try{
                    //两种方法
                    //用http进行网络请求
                    /*
                    URL url=new URL("https://www.wanandroid.com/article/list/0/json");
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream in =connection.getInputStream();
                    //处理
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    String getDate=response.toString();
               //     Log.e("getdata","my data"+getDate);
                    Gson gson=new Gson();
                    Data data =gson.fromJson(getDate,Data.class);
                    */

                    //用okhttp进行网络请求
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("https://www.wanandroid.com/article/list/0/json").build();
                    Response response=client.newCall(request).execute();
                    String getData=response.body().string();
                    Gson gson=new Gson();
                    data =gson.fromJson(getData,Data.class);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myadapter=new rvAdapter(data.data.datas);
                            recyclerView.setAdapter(myadapter);
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (reader!=null){
                        try{
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
        //原本刷新操作放在oncreat()里面的   结果findviewbyid一直返回空指针 放这里一下就运行成功了

        refreshLayout = view.findViewById(R.id.refresh);
        refresh();
        return view;
    }
}