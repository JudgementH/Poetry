package com.judgement.poetry.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.judgement.poetry.R;
import com.judgement.poetry.database.Poetry;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Poetry> poetryList = new ArrayList<>();

    public void setPoetryDataList(List<Poetry> poetryList) {
        this.poetryList = poetryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Poetry poetry = poetryList.get(position);
        String poet = "["+poetry.getDynasty()+"]"+poetry.getPoetName();
        holder.textViewPoet.setText(poet);

        String content = poetry.getContent();
        holder.textViewPoetry.setText(content);
    }

    @Override
    public int getItemCount() {
        return poetryList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewPoetry;
        TextView textViewPoet;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPoetry = itemView.findViewById(R.id.textViewPoetry);
            textViewPoet = itemView.findViewById(R.id.textViewPoet);
        }
    }
}
