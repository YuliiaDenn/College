package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Subject;

public class SubjectDaoImpl extends Dao implements SubjectDao {

	public void addSubject(Subject subject) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "INSERT INTO subject(name) values('" + subject.getName() + "')";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			System.out.println("Subject " + subject + " added!");
			stat.close();
			conn.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Subject " + subject.getName() + " goes there at the base!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateSubject(Subject subject) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "UPDATE subject SET name = '" + subject.getName() + "' WHERE idsubject = '" + subject.getId()
				+ "'";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			System.out.println("Subject updated!");
			System.out.println("New subject: " + subject);
			stat.close();
			conn.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Record not changed!");
			System.out.println("Subject " + subject.getName() + " goes there at the base!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSubject(int id) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "DELETE FROM subject WHERE idsubject = '" + id + "'";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			System.out.println("Subject deleted!");
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Subject getSubjectById(int id) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT * FROM subject WHERE idsubject = '" + id + "' LIMIT 1";
		Subject subject = new Subject();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			subject.setId(rs.getInt(1));
			subject.setName(rs.getString(2));
			System.out.println("Subject " + subject);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Subject by Id " + id + " not found!");
			return null;
		}
		return subject;
	}

	public Subject getSubjectByName(String name) {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT * FROM subject WHERE name = '" + name + "' LIMIT 1";
		Subject subject = new Subject();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			subject.setId(rs.getInt(1));
			subject.setName(rs.getString(2));
			System.out.println("Subject " + subject);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Subject " + name + " not found!");
			return null;
		}
		return subject;
	}

	public List<Subject> getAllSubjects() {

		Connection conn = getConnection();
		Statement stat = null;
		String sql = "SELECT * FROM subject";
		Subject subject = new Subject();
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				subject.setId(rs.getInt(1));
				subject.setName(rs.getString(2));
				subjects.add(subject);
			}
			System.out.println("All subjects: " + subjects);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Subjects table is empty!");
		}
		return subjects;
	}

}
