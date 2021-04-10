package com.github.allanfvc.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.allanfvc.agenda.R;
import com.github.allanfvc.agenda.dao.StudentDAO;
import com.github.allanfvc.agenda.model.Student;

import static com.github.allanfvc.agenda.ui.activity.Constants.STUDENT_KEY;

public class FormStudentActivity extends AppCompatActivity {
  
  private EditText fieldName;
  private EditText fieldPhone;
  private EditText fieldEmail;
  private final StudentDAO dao = new StudentDAO();
  private Student student;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_student);
    

    initFormAttributes();
    loadStudent();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_form_student_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int itemId = item.getItemId();
    if(itemId == R.id.activity_form_student_menu_save){
      submitForm();
    }
    return super.onOptionsItemSelected(item);
  }

  private void initFormAttributes() {
    fieldName = findViewById(R.id.activity_form_student_name);
    fieldPhone = findViewById(R.id.activity_form_student_phone);
    fieldEmail = findViewById(R.id.activity_form_student_email);
  }

  private void initStudent() {
    String name = fieldName.getText().toString();
    String phone = fieldPhone.getText().toString();
    String email = fieldEmail.getText().toString();

    student.setName(name).setPhone(phone).setEmail(email);
  }

  private void submitForm() {
    initStudent();
    if (student.hasValidId()) {
      dao.edit(student);
    } else {
      dao.save(student);
    }
    finish();
  }

  private void loadStudent() {
    Intent data = getIntent();
    if (data.hasExtra(STUDENT_KEY)) {
      setTitle(getResources().getText(R.string.activity_form_students_title_edit));
      student = (Student) data.getSerializableExtra(STUDENT_KEY);
      fillForm();
    } else {
      setTitle(getResources().getText(R.string.activity_form_students_title_new));
      student = new Student();
    }
  }

  private void fillForm() {
    fieldName.setText(student.getName());
    fieldPhone.setText(student.getPhone());
    fieldEmail.setText(student.getEmail());
  }
}