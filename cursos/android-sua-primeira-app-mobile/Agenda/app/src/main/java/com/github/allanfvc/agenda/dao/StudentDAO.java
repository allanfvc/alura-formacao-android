package com.github.allanfvc.agenda.dao;

import com.github.allanfvc.agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
  
  private static final List<Student> students = new ArrayList<>();
  public void save(Student student) {
    students.add(student);
  }

  public List<Student> listAll() {
    return new ArrayList<>(students);
  }
}
