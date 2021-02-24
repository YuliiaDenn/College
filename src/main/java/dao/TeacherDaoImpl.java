package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.Subject;
import entity.Teacher;

public class TeacherDaoImpl extends Dao implements TeacherDao {

	public void addTeacher(Teacher teacher) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "INSERT INTO teacher (idteacher, last_name, name, surname) VALUES( '" + teacher.getId() + "', "
				+ "'" + teacher.getLastName() + "', '" + teacher.getName() + "', '" + teacher.getSurname() + "')";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTeacher(Teacher teacher) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "UPDATE teacher SET last_name = '" + teacher.getLastName() + "', name = '" + teacher.getName()
				+ "', surname = '" + teacher.getSurname() + "' WHERE idteacher = '" + teacher.getId() + "'";

		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTeacher(int id) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "DELETE FROM teacher WHERE idteacher = '" + id + "'";

		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Teacher getTeacherById(int id) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT * FROM teacher  WHERE idteacher = '" + id + "'";
		Teacher teacher = new Teacher();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			teacher.setId(rs.getInt(1));
			teacher.setLastName(rs.getString(2));
			teacher.setName(rs.getString(3));
			teacher.setSurname(rs.getString(4));
			stat.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Teacher by Id " + id + " not found!!!!");
			return null;
		}

		return teacher;
	}

	public Teacher getTeacherByFullName(String lastName, String name, String surname) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT * FROM teacher WHERE last_name = '" + lastName + "'AND name = '" + name
				+ "' AND surname = '" + surname + "' LIMIT 1 ";
		Teacher teacher = new Teacher();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			teacher.setId(rs.getInt(1));
			teacher.setLastName(rs.getString(2));
			teacher.setName(rs.getString(3));
			teacher.setSurname(rs.getString(4));
			stat.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Teacher " + lastName + " " + name + " " + surname + " not found!");
			return null;
		}

		return teacher;
	}

	public List<Teacher> getAllTeachers() {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT * FROM teacher";
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			Teacher teacher;
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setLastName(rs.getString(2));
				teacher.setName(rs.getString(3));
				teacher.setSurname(rs.getString(4));

				teachers.add(teacher);
			}
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teachers;
	}

	public void addSubjectToTeacher(Teacher teacher, Subject subject) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "INSERT  INTO teacher_subject (teacher_id, subject_id) " + "SELECT t.idteacher, s.idsubject "
				+ "FROM teacher t, subject s " + "WHERE t.idteacher =  '" + teacher.getId() + "' AND s.idsubject = '"
				+ subject.getId() + "'";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
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
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Set<Teacher> getAllTeachersBySubject(int id) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT t.idteacher, t.last_name, t.name, t.surname FROM teacher t, teacher_subject ts WHERE ts.subject_id = '"
				+ id + "' AND t.idteacher = ts.teacher_id";
		Set<Teacher> teachers = new HashSet<Teacher>();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()) {
				teachers.add(new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teachers;
	}
}
