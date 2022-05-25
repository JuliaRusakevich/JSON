package com.gmail.juliarusakevich.dao;

import com.gmail.juliarusakevich.dao.entity.group.Group;
import com.gmail.juliarusakevich.dao.api.IGroupDao;
import com.gmail.juliarusakevich.dao.connection.ConnectionManager;
import com.gmail.juliarusakevich.dao.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gmail.juliarusakevich.dao.util.SQLConstant.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class GroupDao implements IGroupDao {

    private static final GroupDao INSTANCE = new GroupDao();

    private GroupDao() {
    }

    @Override
    public Group save(Group entity) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SAVE_GROUP_SQL, RETURN_GENERATED_KEYS)) {
            prepareStatement.setObject(1, entity.getGroupName());
            prepareStatement.executeUpdate();
            var generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("id"));
            return entity;
        } catch (SQLException e) {
            throw new DAOException("Creating group failed. This group name already exist.", e);
        }
    }

    @Override
    public List<Group> findAll() throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_GROUPS_SQL)) {
            var resultSet = prepareStatement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                groups.add(buildGroups(resultSet));
            }
            return groups;
        } catch (SQLException e) {
            throw new DAOException("Unable to return a list of groups.", e);
        }
    }

    @Override
    public void update(Integer id, Group entity) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(UPDATE_GROUP_SQL)) {
            prepareStatement.setObject(1, entity.getGroupName());
            prepareStatement.setObject(2, id);
            var result = prepareStatement.executeUpdate();
            if (result == 0) {//если записей в строке нет
                throw new DAOException("Group wasn't found.");
            }
        } catch (SQLException e) {
            throw new DAOException("Updating group is failed.", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(DELETE_GROUP_SQL)) {
            prepareStatement.setObject(1, id);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Group for removing was not found.", e);
        }
    }

    @Override
    public Optional<Group> findById(Integer id) throws DAOException {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_GROUP_BY_ID_SQL)) {
            var resultSet = prepareStatement.executeQuery();
            Group group = null;
            while (resultSet.next()) {
                group = buildGroups(resultSet);
            }
            return Optional.ofNullable(group);
        } catch (SQLException e) {
            throw new DAOException("Group does not exist", e);
        }
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }

    private Group buildGroups(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("group_name", String.class)
        );
    }

}
