package com.example.demo1.services;

import com.example.demo1.controllers.DBConnection;
import com.example.demo1.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImp implements StudentServiceInterface {

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
             ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double finalScore = rs.getDouble("final_score");
                Student student = new Student(id, name, finalScore);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getStudents(String q, String sort, String dir,int limit) {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        if(dir.isBlank()) {
            dir = "asc";
        }
        if (q != null && !q.isBlank()) {
            sql += " where name like '%" + q + "%'";
        }
        if (sort != null && !sort.isBlank()) {
            sql += " order by " + sort + " " + dir;
        }
        if (limit > 0) {
            sql += " limit " + limit;
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double finalScore = rs.getDouble("final_score");
                Student student = new Student(id, name, finalScore);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "select * from student where id=?";
        Student student = new Student();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setFinalScore(rs.getDouble("final_score"));
                }
            }
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }
        return student;
    }

    @Override
    public void addStudent(Student student) {
        String sql = "insert into student(name,final_score) values(?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, student.getName());
            stmt.setDouble(2, student.getFinalScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "update student set name=?,final_score=? where id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, student.getName());
            stmt.setDouble(2, student.getFinalScore());
            stmt.setInt(3, student.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "delete from student where id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
