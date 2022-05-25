package com.gmail.juliarusakevich.service;

import com.gmail.juliarusakevich.core.dto.group.CreateGroupDto;
import com.gmail.juliarusakevich.core.dto.group.ReadGroupDto;
import com.gmail.juliarusakevich.dao.entity.group.Group;
import com.gmail.juliarusakevich.dao.exception.DAOException;
import com.gmail.juliarusakevich.dao.GroupDao;
import com.gmail.juliarusakevich.service.api.IGroup;
import com.gmail.juliarusakevich.service.exception.ServiceException;
import com.gmail.juliarusakevich.service.mapper.CreateGroupMapper;
import com.gmail.juliarusakevich.service.validator.ValidationException;
import com.gmail.juliarusakevich.service.validator.CreateGroupValidator;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GroupService implements IGroup {

    private static final GroupService INSTANCE = new GroupService();
    private final CreateGroupMapper mapper = CreateGroupMapper.getInstance();
    private final CreateGroupValidator validator = CreateGroupValidator.getInstance();
    private final GroupDao groupDao = GroupDao.getInstance();

    private GroupService() {
    }

    @Override
    public List<ReadGroupDto> find() throws ServiceException {
        try {
            return groupDao.findAll().stream()
                    .map(groupEntity -> new ReadGroupDto(
                            groupEntity.getId(),
                            groupEntity.getGroupName()
                    ))
                    .collect(toList());
        } catch (DAOException e) {
            throw new ServiceException("Unable return all groups.", e);
        }
    }

    @Override
    public void save(CreateGroupDto dto) throws ServiceException {
        var result = validator.isValid(dto);
        if (!result.isValid()) {
            throw new ValidationException(result.getErrors());//будем передавать ошибки
        }
        var groupEntity = mapper.mapFrom(dto);
        try {
            groupDao.save(groupEntity);
        } catch (DAOException e) {
            throw new ServiceException("Unable create group.", e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            if (groupDao.findById(id).isPresent()) {
                groupDao.delete(id);
            }
        } catch (DAOException e) {
            throw new ServiceException("Unable delete group.", e);
        }

    }

    @Override
    public void updateGroupName(Integer id, String name) throws ServiceException {
        try {
            groupDao.update(id, mapToEntity(id, name));
        } catch (DAOException e) {
            throw new ServiceException("Unable update information by group.", e);
        }
    }

    private Group mapToEntity(Integer id, String name) {
        return Group.builder()
                .id(id)
                .groupName(name)
                .build();
    }

    public static GroupService getInstance() {
        return INSTANCE;
    }
}
