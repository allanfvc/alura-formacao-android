package com.github.allanfvc.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.allanfvc.agenda.R;
import com.github.allanfvc.agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends BaseAdapter {
  private Context context;
  private final List<Student> students = new ArrayList<>();

  public ListStudentAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return students.size();
  }

  @Override
  public Student getItem(int position) {
    return students.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).getId();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Student student = getItem(position);
    View view = LayoutInflater.from(context).inflate(R.layout.item_list_students, parent, false);
    TextView studentName = view.findViewById(R.id.item_student_name);
    TextView studentPhone = view.findViewById(R.id.item_student_phone);
    studentName.setText(student.getName());
    studentPhone.setText(student.getPhone());
    return view;
  }

  public void remove(Student student) {
    this.students.remove(student);
    notifyDataSetChanged();
  }
  
  public void update(List<Student> students) {
    this.students.clear();
    this.students.addAll(students);
  }
  
}
