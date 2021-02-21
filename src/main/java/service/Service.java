package service;

import java.util.List;
import java.util.Set;

import dao.SubjectDao;
import dao.SubjectDaoImpl;
import dao.TeacherDao;
import dao.TeacherDaoImpl;
import dao.TeacherSubjectDao;
import dao.TeacherSubjectDaoImpl;
import entity.Subject;
import entity.Teacher;

public class Service {

	TeacherDao teacherDao = new TeacherDaoImpl();
	SubjectDao subjectDao = new SubjectDaoImpl();
	TeacherSubjectDao teachSubDao = new TeacherSubjectDaoImpl();

	public void addTeacher(Teacher teacher) {
		teacherDao.addTeacher(teacher);
	}

	public void updateTeacher(Teacher teacher) {
		teacherDao.updateTeacher(teacher);
	}

	public void deleteTeacher(int id) {
		teacherDao.deleteTeacher(id);
	}

	public Teacher getTeacherByID(int id) {
		return teacherDao.getTeacherById(id);
	}

	public Teacher getTeacherByFullName(String lastName, String name, String surname) {
		return teacherDao.getTeacherByFullName(lastName, name, surname);
	}

	public List<Teacher> getAllTeachers() {
		return teacherDao.getAllTeachers();
	}

	public void addSubject(Subject subject) {
		subjectDao.addSubject(subject);
	}

	public void updateSubject(Subject subject) {
		subjectDao.updateSubject(subject);
	}

	public void deleteSubject(int id) {
		subjectDao.deleteSubject(id);
	}

	public Subject getSubjectByID(int id) {
		return subjectDao.getSubjectById(id);
	}

	public Subject getSubjectByFullName(String name) {
		return subjectDao.getSubjectByName(name);
	}

	public List<Subject> getAllSubjects() {
		return subjectDao.getAllSubjects();
	}

	public void addSubjectToTeacher(Teacher teacher, Subject subject) {
		teachSubDao.addSubjectToTeacher(teacher, subject);
	}

	public void deleteSubjectFromTeacher(int idTeacher, int idSubject) {
		teachSubDao.deleteSubjectFromTeacher(idTeacher, idSubject);
	}

	public Set<Subject> getAllSubjectsFromTeacher(int id) {
		return teachSubDao.getAllSubjectsFromTeacher(id);
	}

	public Set<Teacher> getAllTeacherFromSubject(int id) {
		return teachSubDao.getAllTeacherFromSubject(id);
	}
}
