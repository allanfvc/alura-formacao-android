package com.github.allanfvc.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.allanfvc.agenda.R;
import com.github.allanfvc.agenda.dao.StudentDAO;
import com.github.allanfvc.agenda.model.Student;

public class FormStudentActivity extends AppCompatActivity {

  public static final String APPBAR_TITLE = "Novo aluno";
  private EditText fieldName;
  private EditText fieldPhone;
  private EditText fieldEmail;
  private final StudentDAO dao = new StudentDAO();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(APPBAR_TITLE);
    setContentView(R.layout.activity_form_student);
    

    initFormAttributes();

    Button saveButton = findViewById(R.id.activity_form_student_button_save);
    saveButton.setOnClickListener(v -> {
      Student student = initStudent(fieldName, fieldPhone, fieldEmail);
      save(student);
    });
  }

  private void initFormAttributes() {
    fieldName = findViewById(R.id.activity_form_student_name);
    fieldPhone = findViewById(R.id.activity_form_student_phone);
    fieldEmail = findViewById(R.id.activity_form_student_email);
  }

  private void save(Student student) {
    dao.save(student);
    finish();
  }

  private Student initStudent(EditText fieldName, EditText fieldPhone, EditText fieldEmail) {
    String name = fieldName.getText().toString();
    String phone = fieldPhone.getText().toString();
    String email = fieldEmail.getText().toString();

    return new Student(name, phone, email);
  }
}