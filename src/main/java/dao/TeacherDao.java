package dao;

import java.util.List;
import java.util.Set;

import entity.Subject;
import entity.Teacher;

public interface TeacherDao {

	void addTeacher(Teacher teacher);

	void updateTeacher(Teacher teacher);

	void deleteTeacher(int id);

	Teacher getTeacherById(int id);

	Teacher getTeacherByFullName(String lastName, String name, String surname);

	List<Teacher> getAllTeachers();

	void deleteSubjectFromTeacher(int idTeacher, int idSubject);

	void addSubjectToTeacher(Teacher teacher, Subject subject);

	Set<Subject> getAllSubjectsFromTeacher(int id);

}
