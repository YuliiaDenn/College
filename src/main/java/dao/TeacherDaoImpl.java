package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println("Teacher " + teacher + " added!");
			stat.close();
			conn.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Teacher " + teacher.getLastName() + " " + teacher.getName() + " " + teacher.getSurname()
					+ " goes there at the base!");
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
			System.out.println("Teacher updated!");
			System.out.println("New teacher: " + teacher);
			stat.close();
			conn.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Record not changed!");
			System.out.println("Teacher " + teacher.getLastName() + " " + teacher.getName() + " " + teacher.getSurname()
					+ " goes there at the base!");
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
			System.out.println("Teacher deleted!");
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
			System.out.println("Teacher " + teacher);
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
				+ "' AND surname = '" + surname + "' LIMIT 1";
		Teacher teacher = new Teacher();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			teacher.setId(rs.getInt(1));
			teacher.setLastName(rs.getString(2));
			teacher.setName(rs.getString(3));
			teacher.setSurname(rs.getString(4));
			System.out.println("Teacher " + teacher);
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
			Teacher teacher = new Teacher();
			while (rs.next()) {
				teacher.setId(rs.getInt(1));
				teacher.setLastName(rs.getString(2));
				teacher.setName(rs.getString(3));
				teacher.setSurname(rs.getString(4));
				teachers.add(teacher);
			}
			System.out.println("All teachers: " + teachers);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Teachers table is empty!");
		}

		return teachers;
	}

}
