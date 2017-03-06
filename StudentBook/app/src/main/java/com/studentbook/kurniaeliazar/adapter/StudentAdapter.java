package com.studentbook.kurniaeliazar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studentbook.kurniaeliazar.R;
import com.studentbook.kurniaeliazar.data.Student;

import java.util.List;

/**
 * Created by kurniaeliazar on 3/6/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    List<Student> listStudents;

    public StudentAdapter(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            age = (TextView) view.findViewById(R.id.age);
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
        Student student = listStudents.get(position);
        holder.name.setText(student.getName());
        holder.age.setText(String.valueOf(student.getAge()));
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }
}