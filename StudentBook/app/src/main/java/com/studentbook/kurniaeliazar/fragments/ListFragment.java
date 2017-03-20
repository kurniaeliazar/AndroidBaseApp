package com.studentbook.kurniaeliazar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studentbook.kurniaeliazar.R;
import com.studentbook.kurniaeliazar.adapter.StudentAdapter;
import com.studentbook.kurniaeliazar.data.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;

    List<Student> listStudent = new ArrayList<>();

    public ListFragment() {
        Student studentA = new Student();
        studentA.setName("Mahasiswa A");
        studentA.setAge(24);
        studentA.setFaculty("Teknik Informatika");

        Student studentB = new Student();
        studentB.setName("Mahasiswa B");
        studentB.setAge(25);
        studentB.setFaculty("Teknik Informatika");

        Student studentC = new Student();
        studentC.setName("Mahasiswa C");
        studentC.setAge(24);
        studentC.setFaculty("Teknik Informatika");

        Student studentD = new Student();
        studentD.setName("Mahasiswa D");
        studentD.setAge(23);
        studentD.setFaculty("Teknik Industri");

        Student studentE = new Student();
        studentE.setName("Mahasiswa E");
        studentE.setAge(26);
        studentE.setFaculty("Teknik Industri");

        Student studentF = new Student();
        studentF.setName("Mahasiswa F");
        studentF.setAge(25);
        studentF.setFaculty("Teknik Industri");

        listStudent.add(studentA);
        listStudent.add(studentB);
        listStudent.add(studentC);
        listStudent.add(studentD);
        listStudent.add(studentE);
        listStudent.add(studentF);

//        for(int i=0; i<30 ; i++){
//            listStudent.add(studentA);
//        }

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        studentAdapter = new StudentAdapter(getContext(), listStudent);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(studentAdapter);
        return view;
    }

}
