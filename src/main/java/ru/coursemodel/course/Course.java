package ru.coursemodel.course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private String name;
    private int courseId;
    private float costs;
    private List<Professor> professors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public Course(String name, int courseId, float cost) {
        if (TaskUtils.isCourseValid(name,courseId,cost)){
            throw new RuntimeException("Некорректный ввод для объекта 'Курс'");
        }
        this.name = name;
        this.courseId =courseId;
        this.costs = cost;
    }

    public void addProfessor(Professor professor) {
        if (TaskUtils.canUpdateWithObject(professor,professors)) {
            professors.add(professor);
            professor.addCourse(this);
        }
    }

    public void removeProfessor(Professor professor) {
        if (TaskUtils.canUpdateWithObject(professor,professors)) {
            professors.remove(professor);
            professor.removeCourse(this);
        }
    }

    public void addStudent(Student student) {
        if (TaskUtils.canUpdateWithObject(student,students)) {
            students.add(student);
        }
    }

    public void removeStudent(Student student) {
        if (TaskUtils.canUpdateWithObject(student,students)) {
            students.remove(student);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;

        return  this.name.equals(course.name) &&
                this.courseId == course.courseId &&
                Math.abs(this.costs - course.costs) < 1e-6;
    }

    @Override
    public String toString() {
        return  "Название курса = " +
                name + "\n"+"ID курса = " +
                courseId +"\n"+
                "стоимость курса = " +
                costs+"\n";
    }
}
