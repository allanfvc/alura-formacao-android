package com.github.allanfvc.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.allanfvc.agenda.R;
import com.github.allanfvc.agenda.dao.StudentDAO;
import com.github.allanfvc.agenda.model.Student;
import com.github.allanfvc.agenda.ui.adapter.ListStudentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.github.allanfvc.agenda.ui.activity.Constants.STUDENT_KEY;

public class ListStudentsActivity extends AppCompatActivity {

  private final StudentDAO dao = new StudentDAO();
  private ListStudentAdapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_students);
    setTitle(getResources().getText(R.string.activity_list_students_title));
    setupFabNewStudent();
    setupList();
  }

  @Override
  protected void onResume() {
    super.onResume();
    adapter.update(dao.listAll());
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    getMenuInflater().inflate(R.menu.activity_list_students_menu, menu);
  }

  @Override
  public boolean onContextItemSelected(@NonNull MenuItem item) {
    int itemId = item.getItemId();
    if (itemId == R.id.activity_list_students_remove) {
      confirmDelete(item);
    }

    return super.onContextItemSelected(item);
  }

  private void confirmDelete(@NonNull final MenuItem item) {
    
    new AlertDialog.Builder(this)
      .setTitle("Removendo Aluno")
      .setMessage("Tem certeza que quer remover o Aluno?")
      .setPositiveButton("Sim", (dialog, which) -> {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student selectedStudent = adapter.getItem(menuInfo.position);
        remove(selectedStudent);
      })
      .setNegativeButton("Não", null).show();
  }


  private void setupList() {
    ListView studentsList = findViewById(R.id.activity_list_students_list_view);
    setupAdapter(studentsList);
    setupOnItemClickListener(studentsList);
    registerForContextMenu(studentsList);
  }

  private void setupAdapter(ListView studentsList) {
    adapter = new ListStudentAdapter(this);
    studentsList.setAdapter(adapter);
  }

  private void setupFabNewStudent() {
    FloatingActionButton newStudentButton = findViewById(R.id.activity_list_students_fab_new);
    newStudentButton.setOnClickListener(v -> showFormStudentActivity());
  }

  private void setupOnItemClickListener(ListView studentList) {
      studentList.setOnItemClickListener((adapterView, view, position, id) -> {
        Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
        showFormStudentActivityToEdit(selectedStudent);
      });
  }

  private void showFormStudentActivity() {
    startActivity(new Intent(ListStudentsActivity.this, FormStudentActivity.class));
  }

  private void showFormStudentActivityToEdit(Student student) {
    Intent goToFormStudentActivity = new Intent(ListStudentsActivity.this, FormStudentActivity.class);
    goToFormStudentActivity.putExtra(STUDENT_KEY, student);
    startActivity(goToFormStudentActivity);
  }

  private void remove(Student student) {
    dao.remove(student);
    adapter.remove(student);
  }

}

