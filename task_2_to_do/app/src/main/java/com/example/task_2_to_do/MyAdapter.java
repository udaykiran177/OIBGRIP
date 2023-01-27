package com.example.task_2_to_do;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public Context context;
    private ArrayList<String> task,date;
    DBHelper db;



    public MyAdapter(Context context, ArrayList<String> task, ArrayList<String> date) {
        this.context = context;
        this.task = task;
        this.date = date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.user,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.tasks.setText(task.get(position));
        holder.dates.setText(date.get(position));
    }

    @Override
    public int getItemCount() {
        return task.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tasks,dates;
//        private MyAdapter adapter;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tasks=itemView.findViewById(R.id.task);
            dates=itemView.findViewById(R.id.date);
            itemView.findViewById(R.id.delete).setOnClickListener(view -> {
                db=new DBHelper(context);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Are You sure?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener)(dialog,which)->{
                    Boolean m=db.deleteuserdata(task.get(getAdapterPosition()));
                    if (m){
                        Toast.makeText(context, "Items Removed", Toast.LENGTH_SHORT).show();
                        main_page rel= new main_page();
                        rel.reload();
                    }

                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            });
        }


    }
}
