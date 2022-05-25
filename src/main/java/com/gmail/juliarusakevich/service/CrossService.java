package com.gmail.juliarusakevich.service;

import com.gmail.juliarusakevich.dao.entity.info.InfoGroup;
import com.gmail.juliarusakevich.dao.entity.info.StudentInGroup;
import com.gmail.juliarusakevich.dao.exception.DAOException;
import com.gmail.juliarusakevich.dao.CrossDao;
import com.gmail.juliarusakevich.service.exception.ServiceException;

import java.util.List;

public class CrossService {

    private static final CrossService INSTANCE = new CrossService();

    private final CrossDao dao = CrossDao.getInstance();

    private CrossService() {
    }

    public void addStudentSToGroup(InfoGroup entity) throws ServiceException {
        try {
            dao.add(entity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to complete.", e);
        }
    }

    public void deleteStudentSFromGroup(InfoGroup entity) throws ServiceException {
        try {
            dao.delete(entity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to delete.", e);
        }
    }

    public List<StudentInGroup> returnStudentsAndGroup() throws ServiceException {
        try {
            return dao.find();
        } catch (DAOException e) {
            throw new ServiceException("Unable to return information about groups.", e);
        }

    }

    public static CrossService getInstance() {
        return INSTANCE;
    }
}
