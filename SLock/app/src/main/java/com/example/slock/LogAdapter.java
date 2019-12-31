package com.example.slock;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.CustomViewHolder> {

    private ArrayList<com.example.slock.Log> arrayList;
    private Context context;

    public LogAdapter(ArrayList<com.example.slock.Log> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.time.setText(arrayList.get(position).getTime());
        holder.state.setText(arrayList.get(position).getState());
        if(arrayList.get(position).getState() == "0"){
            holder.image.setImageResource(R.drawable.door_close);
        }
        else{
            holder.image.setImageResource(R.drawable.door_open);
        }
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView time, state;
        ImageView image;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.logTime);
            state = itemView.findViewById(R.id.logState);
            image = itemView.findViewById(R.id.logImage);
        }
    }
}
