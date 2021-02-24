package main;

import java.util.List;

import dao.TeacherDao;
import entity.Subject;
import entity.Teacher;
import service.Service;

public class Main {

	public static void main(String[] args) {
		Service college = new Service();

		System.out.println(college.getTeacherByFullName("Ivanov", "Ivan", "Ivanovych"));
		System.out.println(college.getTeacherByID(8));
		System.out.println(college.getAllTeachers());

		System.out.println(college.getSubjectByID(2));
		System.out.println(college.getSubjectByFullName("Maths"));
		System.out.println(college.getAllSubjects());

		Teacher teacher = new Teacher();
		teacher = college.getTeacherByID(1);
		System.out.println(college.getAllSubjectsFromTeacher(teacher));

		Subject subject2 = college.getSubjectByFullName("Maths");
		System.out.println(college.getAllTeacherFromSubject(subject2));

		System.out.println(college.getAllTeacherFromSubject(college.getSubjectByID(6)));
	}

}
