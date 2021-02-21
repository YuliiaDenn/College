package dao;

import java.util.Set;

import entity.Subject;
import entity.Teacher;

public interface TeacherSubjectDao {

	void addSubjectToTeacher(Teacher teacher, Subject subject);

	void deleteSubjectFromTeacher(int idTeacher, int idSubject);

	Set<Subject> getAllSubjectsFromTeacher(int id);

	Set<Teacher> getAllTeacherFromSubject(int id);
}
