package com.studentbook.kurniaeliazar;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.studentbook.kurniaeliazar.R;
import com.studentbook.kurniaeliazar.data.Student;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailActivity extends Activity {


    TextView name, age, faculty;

    public DetailActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        name = (TextView) findViewById(R.id.detail_name);
        age = (TextView) findViewById(R.id.detail_age);
        faculty = (TextView) findViewById(R.id.detail_faculty);

        Bundle bundle = getIntent().getExtras();
        //Using Parcaleable
        Student student = bundle.getParcelable("student");
        //Using Gson
        //Gson gson = new Gson();
        //Student student = gson.fromJson(bundle.getString("student"), Student.class);
        name.setText(student.getName());
        age.setText(String.valueOf(student.getAge()));
        faculty.setText(student.getFaculty());
    }
}
