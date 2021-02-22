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

	public void addTeacherToSubject(Subject subject, Teacher teacher) {
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

	public void deleteTeacherFromSubject(int idSubject, int idTeacher) {
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

	@Override
	public Set<Subject> getAllSubjectsByTeacher(int id) {
		Connection conn = getConnection();
		Statement stat = null;
		String sql = "select s.idsubject, s.name from subject s, teacher_subject ts where ts.teacher_id = " + id
				+ " and s.idsubject = ts.subject_id";
		Set<Subject> subjects = new HashSet<Subject>();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				subjects.add(new Subject(rs.getInt(1), rs.getString(2)));
			}
			System.out.println("All subjects from taecher: " + subjects);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

}
