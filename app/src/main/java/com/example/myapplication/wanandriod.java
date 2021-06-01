package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class wanandriod extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanandriod);
        ViewPager2 viewPager2=findViewById(R.id.vp2);
        //设置动画
        viewPager2.setPageTransformer(new zoomoutpagetransformer());
        //
        BottomNavigationView bottomNavigationView= findViewById(R.id.nav_view);

        //建立fragment arraylist
        ArrayList<Fragment> list=new ArrayList<>();
        list.add(new homeFragment());
        list.add(new peopleFragment());

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @NotNull
            @Override
            public Fragment createFragment(int position) {
                return list.get(position);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });
        //viewpage2找菜单按键
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);//将对应的按键点击状态设为true，position就是menu里面设置的order
            }
        });

        //通过item找对应的fragment
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                viewPager2.setCurrentItem(item.getOrder());
                return true;
            }
            });
    }

}