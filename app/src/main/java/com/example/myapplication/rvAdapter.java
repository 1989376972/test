package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder>{
    List<artical> mArtical;
    //构造方法
    public rvAdapter (List<artical> list){
        this.mArtical=list;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.artical,parent,false);
        ViewHolder holder=new ViewHolder(v);
        holder.articalview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= holder.getAdapterPosition();
                artical artical=mArtical.get(position);
                Intent intent=new Intent(v.getContext(),YourArtical.class);
                intent.putExtra("link",artical.link);
                parent.getContext().startActivity(intent);
                Toast.makeText(v.getContext(),"你点击了 "+artical.title,Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        artical artical=mArtical.get(position);
        holder.title.setText(artical.title);
        holder.time.setText(artical.nicedata);
        holder.username.setText(artical.uersername);
    }

    @Override
    public int getItemCount() {
        return mArtical.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
       TextView title;
       TextView time;
       TextView username;
       View articalview;
        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            articalview=itemView;
            title=itemView.findViewById(R.id.articaltitle);
            time=itemView.findViewById(R.id.time);
            username=itemView.findViewById(R.id.username);
        }
    }
}
