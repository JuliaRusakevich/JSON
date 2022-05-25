package com.gmail.juliarusakevich.dao;

import com.gmail.juliarusakevich.dao.entity.student.Student;
import com.gmail.juliarusakevich.dao.api.IStudentDao;
import com.gmail.juliarusakevich.dao.connection.ConnectionManager;
import com.gmail.juliarusakevich.dao.exception.DAOException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gmail.juliarusakevich.dao.util.SQLConstant.*;

public class StudentDao implements IStudentDao {

    private static final StudentDao INSTANCE = new StudentDao();

    private StudentDao() {
    }

    @Override
    public Student save(Student entity) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SAVE_STUDENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setObject(1, entity.getName());
            prepareStatement.setObject(2, entity.getAge());
            prepareStatement.setObject(3, entity.getScore());
            prepareStatement.setObject(4, entity.getOlympicGamer());
            prepareStatement.executeUpdate();
            var generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;
        } catch (SQLException e) {
            throw new DAOException("Creating student was failed.", e);
        }
    }

    @Override
    public List<Student> findAll() throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_STUDENTS_SQL)) {
            var resultSet = prepareStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudents(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DAOException("Unable to return a list of students.", e);
        }
    }

    @Override
    public void update(Integer id, Student entity) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
            prepareStatement.setObject(1, entity.getName());
            prepareStatement.setObject(2, entity.getAge());
            prepareStatement.setObject(3, entity.getScore());
            prepareStatement.setObject(4, entity.getOlympicGamer());
            prepareStatement.setObject(5, entity.getId());
            var result = prepareStatement.executeUpdate();
            if (result == 0) {
                throw new DAOException("Student wasn't found.");
            }
        } catch (SQLException e) {
            throw new DAOException("Updating student info was failed.", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            prepareStatement.setObject(1, id);
            if (prepareStatement.executeUpdate() == 0) {
                throw new DAOException("Student for removing was not found.");
            } else {
                return prepareStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Removing student was failed.", e);
        }
    }

    @Override
    public Optional<Student> findById(Integer id) throws DAOException {
        return Optional.empty();
    }

    public static StudentDao getInstance() {
        return INSTANCE;

    }

    private Student buildStudents(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("age", Integer.class),
                resultSet.getObject("score", BigDecimal.class).doubleValue(),
                resultSet.getObject("olympic_gamer", Boolean.class)
        );
    }

}
