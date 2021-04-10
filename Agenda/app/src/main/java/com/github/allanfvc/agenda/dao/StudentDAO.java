package com.github.allanfvc.agenda.dao;

import com.github.allanfvc.agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
  
  private static final List<Student> students = new ArrayList<>();
  private static int contadorDeIds = 1;
  
  public void save(Student student) {
    student.setId(contadorDeIds);
    students.add(student);
    contadorDeIds++;
  }

  public List<Student> listAll() {
    return new ArrayList<>(students);
  }
  
  public void remove(Student student) {
    int position = findPositionById(student);
    students.remove(position);
  }

  public void edit(Student student) {
    int position = findPositionById(student);
    students.set(position, student);
  }

  private int findPositionById(Student student) {
    for (int i = 0; i < students.size(); i++) {
      if(student.getId() == students.get(i).getId()) {
        return i;
      }
    }
    return -1;
  }
}
