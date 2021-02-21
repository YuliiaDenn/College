package dao;

import java.util.List;

import entity.Subject;

public interface SubjectDao {

	void addSubject(Subject subject);

	void updateSubject(Subject subject);

	void deleteSubject(int id);

	Subject getSubjectById(int id);

	Subject getSubjectByName(String name);

	List<Subject> getAllSubjects();

}
