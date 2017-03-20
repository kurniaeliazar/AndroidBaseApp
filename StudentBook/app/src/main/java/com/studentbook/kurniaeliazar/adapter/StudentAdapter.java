package com.studentbook.kurniaeliazar.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studentbook.kurniaeliazar.R;
import com.studentbook.kurniaeliazar.data.Student;
import com.studentbook.kurniaeliazar.DetailActivity;

import java.util.List;

/**
 * Created by kurniaeliazar on 3/6/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    List<Student> listStudents;
    Context context;

    public StudentAdapter(Context context, List<Student> listStudents) {
        this.listStudents = listStudents;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age;
        public LinearLayout frame;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            age = (TextView) view.findViewById(R.id.age);
            frame = (LinearLayout) view.findViewById(R.id.groupLayout);
        }
    }

    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_student, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.MyViewHolder holder, int position) {
        final Student student = listStudents.get(position);
        holder.name.setText(student.getName());
        holder.age.setText(String.valueOf(student.getAge()));

        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();

                //Using gson
                //Gson gson = new Gson();
                //bundle.putString("student", gson.toJson(student));
                //Log.d("DATA", gson.toJson(student));

                //Using Parcalable
                bundle.putParcelable("student", student);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }
}