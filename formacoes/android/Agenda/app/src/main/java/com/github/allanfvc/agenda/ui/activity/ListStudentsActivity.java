package com.github.allanfvc.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.allanfvc.agenda.R;
import com.github.allanfvc.agenda.dao.StudentDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListStudentsActivity extends AppCompatActivity {

  public static final String APPBAR_TITLE = "Lista de alunos";

  @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_list_students);
      setTitle(APPBAR_TITLE);
    setupFabNewStudent();
  }

  private void setupFabNewStudent() {
    FloatingActionButton newStudentButton = findViewById(R.id.activity_list_students_fab_new);
    newStudentButton.setOnClickListener(v -> showFormStudentActivity());
  }

  private void showFormStudentActivity() {
    startActivity(new Intent(ListStudentsActivity.this, FormStudentActivity.class));
  }

  @Override
  protected void onResume() {
    super.onResume();
    StudentDAO dao = new StudentDAO();
    ListView studentsList = findViewById(R.id.activity_list_students_listview);
    studentsList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.listAll()));
  }
}

