package com.example.finalproject.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Homework;
import com.example.finalproject.Model.Task;
import com.example.finalproject.R;

import java.util.List;

public class HmwrkRecyclerViewAdapter extends RecyclerView.Adapter<HmwrkRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Homework> hmwrkList;
    private onItemClickListener2 mListener;
    private CheckBox checkBox;

    public interface onItemClickListener2{
        void onDelete(int position);

        void longClick(int position);
    }

    public void setOnItemClickListener2(onItemClickListener2 listener){mListener =listener;}

    public HmwrkRecyclerViewAdapter(Context context, List<Homework> hmwrkList) {
        this.context = context;
        this.hmwrkList = hmwrkList;
    }

    @NonNull
    @Override
    public HmwrkRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hmwrk_row,parent,false);

        return new HmwrkRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HmwrkRecyclerViewAdapter.ViewHolder holder, int position) {
        Homework hmwrk = hmwrkList.get(position);
        holder.name.setText(hmwrk.getHwrkName());
        holder.description.setText(hmwrk.getHmwkClass()+" : " + hmwrk.getHmwkDescirption());
    }

    @Override
    public int getItemCount() {
        return hmwrkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.hmwrkNameRow);
            description = itemView.findViewById(R.id.hmwrkDescriptionRow);
            checkBox = itemView.findViewById(R.id.hmwrkCheck);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onDelete(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.longClick(position);
                            return true;
                        }
                    }
                    return false;
                }
            });

        }
    }
}
