package com.github.allanfvc.agenda.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.github.allanfvc.agenda.dao.StudentDAO;
import com.github.allanfvc.agenda.model.Student;
import com.github.allanfvc.agenda.ui.adapter.ListStudentAdapter;

public class ListStudentsView {

  private final Context context;
  private final ListStudentAdapter adapter;
  private final StudentDAO dao;

  public ListStudentsView(Context context) {
    this.context = context;
    this.adapter = new ListStudentAdapter(context);
    this.dao = new StudentDAO();
  }

  public void confirmDelete(@NonNull final MenuItem item) {

    new AlertDialog.Builder(context)
      .setTitle("Removendo Aluno")
      .setMessage("Tem certeza que quer remover o Aluno?")
      .setPositiveButton("Sim", (dialog, which) -> {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student selectedStudent = adapter.getItem(menuInfo.position);
        remove(selectedStudent);
      })
      .setNegativeButton("Não", null).show();
  }

  public void setupAdapter(ListView studentsList) {
    studentsList.setAdapter(adapter);
  }

  private void remove(Student student) {
    dao.remove(student);
    adapter.remove(student);
  }
  
  public void update() {
    adapter.update(dao.listAll());
  }
}
