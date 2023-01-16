package com.dewansh.todoapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dewansh.todoapp.Interfaces.RecyclerViewClickListener;
import com.dewansh.todoapp.Models.TodoModel;
import com.dewansh.todoapp.R;

import java.util.ArrayList;
import java.util.Random;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder> {

    ArrayList<TodoModel> arrayList;
    Context context;
    final private RecyclerViewClickListener clickListener;

    public TodoListAdapter(Context context, ArrayList<TodoModel> arrayList, RecyclerViewClickListener clickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.todo_list_item, parent, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);

        int[] androidColors = view.getResources().getIntArray(R.array.androidcolors);
        int randomColors = androidColors[new Random().nextInt(androidColors.length)];

        myViewHolder.accordian_title.setBackgroundColor(randomColors);

        myViewHolder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewHolder.accordian_body.getVisibility() == View.VISIBLE) {
                    myViewHolder.accordian_body.setVisibility(View.GONE);
                } else {
                    myViewHolder.accordian_body.setVisibility(View.VISIBLE);
                }
            }
        });
        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdapter.MyViewHolder holder, int position) {

        final String title = arrayList.get(position).getTitle();
        final String description = arrayList.get(position).getDescription();
        final String id = arrayList.get(position).getId();

        holder.titleTv.setText(title);
        if(!description.equals("")) {
            holder.descriptionTv.setText(description);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView accordian_title;
        TextView titleTv, descriptionTv;
        RelativeLayout accordian_body;
        ImageView arrow, editBtn, deleteBtn, doneBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv =  itemView.findViewById(R.id.task_title);
            descriptionTv =  itemView.findViewById(R.id.task_description);
            accordian_title = itemView.findViewById(R.id.accordian_title);
            accordian_body =  itemView.findViewById(R.id.accordian_body);
            arrow = itemView.findViewById(R.id.arrow);
            editBtn =  itemView.findViewById(R.id.editBtn);
            deleteBtn =  itemView.findViewById(R.id.deleteBtn);
            doneBtn = itemView.findViewById(R.id.doneBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onEditButtonClick(getAdapterPosition());
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteButtonClick(getAdapterPosition());
                }
            });

            doneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDoneButtonClick(getAdapterPosition());
                }
            });


        }
    }
}
