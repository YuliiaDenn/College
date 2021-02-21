package dao;

import java.util.List;

import entity.Teacher;

public interface TeacherDao {

	void addTeacher(Teacher teacher);

	void updateTeacher(Teacher teacher);

	void deleteTeacher(int id);

	Teacher getTeacherById(int id);

	Teacher getTeacherByFullName(String lastName, String name, String surname);

	List<Teacher> getAllTeachers();

}
