package com.gmail.juliarusakevich.dao;

import com.gmail.juliarusakevich.dao.connection.ConnectionManager;
import com.gmail.juliarusakevich.dao.entity.info.InfoGroup;
import com.gmail.juliarusakevich.dao.entity.info.ReadStudentInGroup;
import com.gmail.juliarusakevich.dao.entity.info.StudentId;
import com.gmail.juliarusakevich.dao.entity.info.StudentInGroup;
import com.gmail.juliarusakevich.dao.exception.DAOException;
import jd2.HelloServlet;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.juliarusakevich.dao.util.SQLConstant.*;


public class CrossDao {

    private static final CrossDao instance = new CrossDao();

    private CrossDao() {
    }

    public boolean add(InfoGroup entity) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(ADD_STUDENTS_TO_GROUP_SQL)) {

            var groupId = entity.getGroupId();
            var list = entity.getList();

            for (StudentId student : list) {
                prepareStatement.setObject(1, groupId);
                prepareStatement.setObject(2, student.getStudentId());
                prepareStatement.addBatch();
            }
            var result = prepareStatement.executeBatch();

            return result.length != 0;
        } catch (SQLException e) {
            throw new DAOException("Student or group wasn't found or student presents in group.", e);
        }
    }

    public boolean delete(InfoGroup entity) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(DELETE_STUDENTS_FROM_GROUP_SQL)) {
            var groupId = entity.getGroupId();
            var list = entity.getList();
            for (StudentId student : list) {
                prepareStatement.setObject(1, student.getStudentId());
            }
            var result = prepareStatement.executeUpdate();
            if (result == 0) {
                throw new DAOException("Student wasn't found.");
            }

            return result != 0;
        } catch (SQLException e) {
            throw new DAOException("Oops.", e);
        }
    }

    public List<StudentInGroup> find() throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_GROUPS_AND_STUDENTS_SQL)) {
            var rs = prepareStatement.executeQuery();

            List<StudentInGroup> list = new ArrayList<>();
            List<ReadStudentInGroup> students = null;

            while (rs.next()) {

                var groupName = rs.getObject("group_name", String.class);
                var studentName = rs.getObject("name", String.class);
                var score = rs.getObject("score", BigDecimal.class).doubleValue();
                var isOlympicGame = rs.getObject("olympic_gamer", Boolean.class);

                list.add(new StudentInGroup(groupName, students));

                //students.add(new ReadStudentInGroup(studentName, score, isOlympicGame));

            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Беда!!!!", e);
        }
    }

    public static CrossDao getInstance() {
        return instance;
    }
/*
    private StudentInGroup getGroupInfo(ResultSet resultSet) throws SQLException {
        return new StudentInGroup(
                resultSet.getObject("group_name", String.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("score", BigDecimal.class).doubleValue(),
                resultSet.getObject("olympic_gamer", Boolean.class)
        );
    }

 */


}
