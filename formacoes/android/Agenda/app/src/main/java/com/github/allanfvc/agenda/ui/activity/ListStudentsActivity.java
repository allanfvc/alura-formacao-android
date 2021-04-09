package com.github.allanfvc.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.allanfvc.agenda.R;
import com.github.allanfvc.agenda.dao.StudentDAO;
import com.github.allanfvc.agenda.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.github.allanfvc.agenda.ui.activity.Contants.STUDENT_KEY;

public class ListStudentsActivity extends AppCompatActivity {

  public static final String APPBAR_TITLE = "Lista de alunos";
  private final StudentDAO dao = new StudentDAO();
  private ArrayAdapter<Student> adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_students);
    setTitle(APPBAR_TITLE);
    setupFabNewStudent();
  }

  @Override
  protected void onResume() {
    super.onResume();
    StudentDAO dao = new StudentDAO();
    setupList(dao);
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
      AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
      Student selectedStudent = adapter.getItem(menuInfo.position);
      remove(selectedStudent);
    }

    return super.onContextItemSelected(item);
  }


  private void setupList(StudentDAO dao) {
    ListView studentsList = findViewById(R.id.activity_list_students_listview);
    setupAdapter(dao, studentsList);
    setupOnItemClickListener(studentsList);
    registerForContextMenu(studentsList);
  }

  private void setupAdapter(StudentDAO dao, ListView studentsList) {
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.listAll());
    studentsList.setAdapter(adapter);
  }

  private void setupFabNewStudent() {
    FloatingActionButton newStudentButton = findViewById(R.id.activity_list_students_fab_new);
    newStudentButton.setOnClickListener(v -> showFormStudentActivity());
  }

  private void setupOnItemClickListener(ListView studentList) {
      studentList.setOnItemClickListener((adapterView, view, posicao, id) -> {
        Student selectedStudent = (Student) adapterView.getItemAtPosition(posicao);
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
