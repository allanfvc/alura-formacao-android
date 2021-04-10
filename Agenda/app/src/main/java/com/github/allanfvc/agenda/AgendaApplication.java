package com.github.allanfvc.agenda;

import android.app.Application;

import com.github.allanfvc.agenda.dao.StudentDAO;
import com.github.allanfvc.agenda.model.Student;

public class AgendaApplication  extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    setupTestStudents();
  }

  private void setupTestStudents() {
    StudentDAO dao = new StudentDAO();
    dao.save(new Student("Allan", "11 2222 3333", "allanfvc@gmail.com"));
    dao.save(new Student("Fran", "11 2222 3333", "fran@gmail.com"));
  }
}
