package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import entity.Subject;
import entity.Teacher;

public class TeacherSubjectDaoImpl extends Dao implements TeacherSubjectDao {

	private SubjectDao subjectDao = new SubjectDaoImpl();
	private TeacherDao teacherDao = new TeacherDaoImpl();

	public void addSubjectToTeacher(Teacher teacher, Subject subject) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "INSERT  INTO teacher_subject (teacher_id, subject_id) " + "SELECT t.idteacher, s.idsubject "
				+ "FROM teacher t, subject s " + "WHERE t.idteacher =  '" + teacher.getId() + "' AND s.idsubject = '"
				+ subject.getId() + "'";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			System.out.println("Created a pair of " + teacher + " - " + subject + "!");
			stat.close();
			conn.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("The pair of " + teacher + " - " + subject + " goes there at the base!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteSubjectFromTeacher(int idTeacher, int idSubject) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "DELETE FROM teacher_subject WHERE teacher_id = '" + idTeacher + "' AND subject_id = '" + idSubject
				+ "'";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			System.out.println("Deleted!");
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Set<Subject> getAllSubjectsFromTeacher(int id) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT subject_id FROM teacher_subject WHERE teacher_id = '" + id + "'";
		Set<Subject> subjects = new HashSet<Subject>();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()) {
				Subject subject = subjectDao.getSubjectById(rs.getInt(1));
				subjects.add(subject);
			}
			System.out.println("All subjects from " + teacherDao.getTeacherById(id) + ": " + subjects);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjects;
	}

	public Set<Teacher> getAllTeacherFromSubject(int id) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT teacher_id FROM teacher_subject WHERE  subject_id = '" + id + "'";
		Set<Teacher> teachers = new HashSet<Teacher>();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()) {
				Teacher teacher = teacherDao.getTeacherById(rs.getInt(1));
				teachers.add(teacher);
			}
			System.out.println("All taechers from " + subjectDao.getSubjectById(id) + ": " + teachers);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teachers;
	}

}
