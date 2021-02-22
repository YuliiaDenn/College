package dao;

import java.util.List;
import java.util.Set;

import entity.Subject;
import entity.Teacher;

public interface SubjectDao {

	void addSubject(Subject subject);

	void updateSubject(Subject subject);

	void deleteSubject(int id);

	Subject getSubjectById(int id);

	Subject getSubjectByName(String name);

	List<Subject> getAllSubjects();

	void addTeacherToSubject(Subject subject, Teacher teacher);

	void deleteTeacherFromSubject(int idSubject, int idTeacher);

	Set<Subject> getAllSubjectsByTeacher(int id);

}
