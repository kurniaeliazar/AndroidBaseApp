package com.studentbook.kurniaeliazar.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kurniaeliazar on 3/6/17.
 */

public class Student implements Parcelable {

    private String name;
    private int age;
    private String faculty;

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(faculty);
    }

    private Student(Parcel in){
        this.name = in.readString();
        this.age = in.readInt();
        this.faculty = in.readString();
    }

    public Student(){

    }

}
